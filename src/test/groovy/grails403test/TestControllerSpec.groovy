package grails403test

import grails.testing.web.controllers.ControllerUnitTest
import spock.lang.Specification

class TestControllerSpec extends Specification implements ControllerUnitTest<TestController> {

    def setup() {
    }

    def cleanup() {
    }

    void "test json"(){
        when:
        request.json = [
                a:1, b:2, c:3
        ]
        controller.testJson()

        then:
        response.json.a == 1
        response.json.b == 2
        response.json.c == 3

        when:
        response.reset()
        request.json = [
                a:10, b:20, c:30
        ]
        controller.testJson()

        then:
        response.json.a == 10 // <-- test failed here
        response.json.b == 20
        response.json.c == 30
    }
}
