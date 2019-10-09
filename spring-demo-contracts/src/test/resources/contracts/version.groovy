import org.springframework.cloud.contract.spec.Contract

import java.util.regex.Pattern


Pattern datetime = Pattern.compile(/([0-9]{4})-(1[0-2]|0[1-9])-(3[01]|0[1-9]|[12][0-9])T(2[0-3]|[01][0-9]):([0-5][0-9]):([0-5][0-9])(\.\d+)/)
Pattern str = Pattern.compile(/[a-zA-Z]{10}/)
Pattern version = Pattern.compile(/\d{1,2}\.\d{1,2}\.\d{1,2}/)
//example -  {"commitTime":"2018-10-30T11:04:04.296","commit":"AAAAABBBBB","version":"0.16.0","branch":"developAAA"}
Contract.make {
    description "should return even when number input is even"
    request {
        method GET()
        url("/impl/version")
    }
    response {
        headers {contentType applicationJson()}
        body([
                "version": regex(version),
                "commitTime": regex(datetime),
                "commit": regex(str),
                "branch" : regex(str)
        ])
        status 200
    }

}