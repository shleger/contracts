import org.springframework.cloud.contract.spec.Contract

import java.util.regex.Pattern


Pattern gitDateTimeWithOffset = Pattern.compile(/([0-9]{4})-(1[0-2]|0[1-9])-(3[01]|0[1-9]|[12][0-9])T(2[0-3]|[01][0-9]):([0-5][0-9]):([0-5][0-9])(\.\d+)/)

//example -  {"commitTime":"2018-10-30T11:04:04.296","commit":"0e0e8916293b8d509a681cfb55d8c6eee0851c38","version":"0.16.0-SNAPSHOT","branch":"develop2"}
Contract.make {
    description "should return even when number input is even"
    request {
        method GET()
        url("/impl/version") {

        }
    }
    response {
        headers {contentType applicationJson()}
        body([
                version: regex(nonEmpty()),
                commitTime: gitDateTimeWithOffset,
                commit: regex(nonEmpty()),
                branch : regex(nonEmpty())
        ])
        status 200
    }

}