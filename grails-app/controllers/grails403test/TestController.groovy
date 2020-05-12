package grails403test

class TestController {
    def testJson(){
        def json = request.JSON
        render(contentType:'application/json', text:json.toString(), encoding: "UTF-8")
    }
}
