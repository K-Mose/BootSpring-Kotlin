package com.example.mvc.controller.response

import com.example.mvc.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/response")
class ResponseApiController {

    // 1. get - 4xx
    // GET localhost:8080/api/response?age=
    @GetMapping("")
    fun getMapping(@RequestParam age: Int?): ResponseEntity<String> {

        return age?.let{
            // age is not null
            if(it < 20){
                return ResponseEntity.badRequest().body("fail\n 값이 20보다 커야 함")
            }
            return ResponseEntity.ok("OK.")
        }?: kotlin.run {
            // age is null
            ResponseEntity.status(400).body("fail\n 값 없음")
        }
        /*
        // 1. age == null -> 400
        if(age == null)
            return ResponseEntity.status(400).body("fail\n 값 없음")

        // 2. age < 20 -> 400
        if(age < 20)
            return ResponseEntity.badRequest().body("fail\n 값이 20보다 커야 함")

        println(age)
        return ResponseEntity.ok("OK.")
         */
    }

    // 2. post - 200
    @PostMapping("")
    fun postMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<Any> {
        return ResponseEntity.status(200).body(userRequest)
    }

    // 3. put - 201
    @PutMapping("")
    fun putMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<UserRequest> {
        // 1. 기존 데이터가 없어서 새로 생성했다.
        return ResponseEntity.status(HttpStatus.CREATED).body(userRequest)
    }

    // 4. delete - 500
    @DeleteMapping("/{id}")
    fun deleteMapping(@PathVariable id : Int): ResponseEntity<Any> {
//        return ResponseEntity.status(500).body(null)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
    }
}