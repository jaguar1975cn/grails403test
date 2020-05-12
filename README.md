## Project Description

A simple project to demonstrate a failed unit test for controller

## Controller class and the test
```groovy
class TestController {
    def testJson(){
        def json = request.JSON
        render(contentType:'application/json', text:json.toString(), encoding: "UTF-8")
    }
}
```
```groovy
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
```

## Requirement
Use [SDK Man](https://sdkman.io/install) to install java and GRAILS, e.g.

```bash
$ sdk install java 8.0.252-zulu
$ sdk install grails 4.0.3
```

## Run the test
```bash
$ cd Grails403Test
$ grails test-app
```

## Output
```
Condition not satisfied:

response.json.a == 10 // <-- test failed here
|        |    | |
|        |    1 false
|        [a:1, b:2, c:3]
```