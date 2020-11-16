package com.example.mvc.controller.get

import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.*

@RestController // REST API Controller 동작
@RequestMapping("/api") // http://localhost:8080/api
class GetApiController {

    // path 값으로 주면 여러 주소가 들어감.
    @GetMapping(path=["/hello","/abcd"]) // Get http://localhost:8080/api/hello
    fun hello():String{
        return "hello kotlin"
    }

    // method는 Array 형태로 지정 가능
    @RequestMapping(method = [RequestMethod.GET],path = ["/request-mapping"]) // GET PUT POST DELETE 다 동작.
    fun requestMapping(): String {
        return "Request - Get Mapping"
    }

    // Get http://localhost:8080/api/get-mapping/path-variable/name
    @GetMapping("/get-mapping/path-variable/{name}/{age}")
    fun pathVariable(@PathVariable name:String, @PathVariable  age:Int):String{
        println("$name : $age ")
        return name+ " : " + age
    }

    // Get http://localhost:8080/api/get-mapping/path-variable/name
    @GetMapping("/get-mapping/path-variable2/{name}/{age}")
    fun pathVariable2(@PathVariable(value = "name") _name:String, @PathVariable(name="age")  age:Int):String{
        // @PathVariable(value = "name") _name:String
        // URL에 직접 적용
        val name = "Kotlin"

        println("$_name : $age ")
        return _name+ " : " + age
    }

    // query Parameter
    // https://localhost:8080/api/page?key=value&key2=value2 ....
    @GetMapping("get-mapping/query-param") // ?name=MOSE&age=27
    fun queryParam(
            @RequestParam(value="name") myName:String,
            @RequestParam(value="age") age:Int
    ):String{
        println("$myName , $age")
        return "$myName , $age"
    }

    // 객체 맵핑
    // name, age, address, email, occupation
    @GetMapping("get-mapping/query-param/object")
    fun queryParamObject(userRequest: UserRequest):UserRequest{
        println(userRequest)
        return userRequest
    }

    @GetMapping("/get-mapping/query-param/map")
    fun queryParamMap(@RequestParam map:Map<String,Any>): Map<String, Any> {
        val phoneNumber = map.get("phone-number")
        println("$map $phoneNumber")
        return map
    }
}