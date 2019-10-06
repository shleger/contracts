import org.springframework.cloud.contract.spec.Contract

import java.util.regex.Pattern

Pattern any = Pattern.compile(/.*/)
Pattern anyOfStatus = Pattern.compile(/START_ACTION|END_ACTION/)
Pattern anyLogin = Pattern.compile(/[a-zA-Z0-9_]*/)
static def anyInteger() { return Pattern.compile(/[\d]*/) }

[
        Contract.make {
            description "should return ActionDto and compare with response.json"
            request {
                method GET()
                url("/action/5") {

                }
            }
            response {
                headers {
                    contentType(applicationJson())
                }
                status 200
                body file("response.json")
                bodyMatchers {
                    jsonPath('$.uidForCalcParams', byRegex(regex(uuid())))
                    jsonPath('$.uid', byRegex(regex(uuid())))
                    jsonPath('$.id', byRegex(anyInteger()))
                    jsonPath('$.creditSum', byRegex(anyInteger()))
                    jsonPath('$.fraudDecision.id', byRegex(anyInteger()))
                    jsonPath('$.fraudDecision.assignee', byRegex(anyLogin))
                    jsonPath('$.supervisorDecision.id', byRegex(anyInteger()))
                    jsonPath('$.supervisorDecision.assignee', byRegex(anyLogin))
                    jsonPath('$.assigneeLogin', byRegex(anyLogin))
                    jsonPath('$.status.code', byRegex(anyOfStatus))
                    jsonPath('$.personalInsurance.name', byRegex(any))
                    jsonPath('$.requesterSystem.name', byRegex(any))
                }

            }

        },

        Contract.make {
            description "should  compare results using regex"
            request {
                method GET()
                url("/action/6")
            }

            response {
                headers { contentType applicationJson() }
                status 200
                body([
                        "fraudDecision"              : ([
                                "decisionType"  : ([
                                        "name": any,
                                        "code": anyOf("FRAUD_CONFIRMED_ORDER", "FRAUD_NOT_CONFIRMED_ORDER")
                                ]),
                                "entityType"    : "order",
                                "publicComment" : any,
                                "privateComment": any,
                                "assignee"      : anyLogin,
                                "id"            : regex(number())
                        ]),
                        "supervisorDecision"         : ([
                                "decisionType"  : ([
                                        "name": any,
                                        "code": anyOf("SUPERVISOR_NOT_CONFIRMED_ORDER", "SUPERVISOR_CONFIRMED_ORDER")
                                ]),
                                "entityType"    : "order",
                                "publicComment" : any,
                                "privateComment": any,
                                "assignee"      : anyLogin,
                                "id"            : regex(number())
                        ]),
                        "requesterSystem"            : ([
                                "code": anyOf("OLD", "NEW"),
                                "name": any
                        ]),
                        "uid"                        : regex(uuid()),
                        "uidForCalcParams"           : value(producer(optional(regex(uuid())))),
                        "calcPerformed"              : regex(anyBoolean()),
                        "creditSum"                  : regex(number()),
                        "creditTerm"                 : regex(number()),
                        "personalInsurance"          : ([
                                "name": any,
                                "code": anyOf("ENABLED", "DISABLED", "NOT_SET")
                        ]),
                        "status"                     : any,
                        "calculationResult"          : regex(number()),
                        "controllerApprovalNecessity": regex(anyBoolean()),
                        "assigneeLogin"              : anyLogin,
                        "id"                         : regex(number())
                ])

                bodyMatchers {
                    jsonPath('$.uidForCalcParams', byRegex(uuid()))
                    jsonPath('$.uid', byRegex(uuid()))
                    jsonPath('$.id', byRegex(anyInteger().pattern()))
                }


            }

        }

]
