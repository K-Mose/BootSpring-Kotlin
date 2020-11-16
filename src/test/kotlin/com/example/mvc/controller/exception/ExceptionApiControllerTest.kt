package com.example.mvc.controller.exception

import com.example.mvc.model.http.UserRequest
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.util.LinkedMultiValueMap
import java.time.LocalDateTime

@WebMvcTest // 웹 MVC 관련 기능만 로드
@AutoConfigureMockMvc // 자동으로 MockMvc 설정을 함
class ExceptionApiControllerTest {

    @Autowired // 스프링에서 자동으로 주입 시킴
    lateinit var mockMvc:MockMvc

    @Test
    fun helloTest(){
        mockMvc.perform(
                get("/api/exception/hello")
        ).andExpect(
            status().isBadRequest
        ).andExpect{
            content().string("Hello")
        }.andDo(print())
    }

    @Test
    fun getTest(){
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name","mose")
        queryParams.add("age","27")

        mockMvc.perform(
                get("/api/exception").queryParams(queryParams)
        ).andExpect(
                status().isOk
        ).andExpect(
                content().string("mose  27")
        ).andDo(print())
    }

    @Test
    fun getFailTest(){
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name","mose")
        queryParams.add("age","9")

        mockMvc.perform(
                get("/api/exception").queryParams(queryParams)
        ).andExpect(
                status().isBadRequest
        ).andExpect(
                content().contentType("application/json")
        ).andExpect(
                jsonPath("\$.result_code").value("FAIL")
        )
        .andExpect(
                jsonPath("\$.errors[0].field").value("age")
        ).andExpect(
                jsonPath("\$.errors[0].value").value(9)
        ).andExpect(
                jsonPath("\$.http_status").value(400)
        ).andDo(print())
    }

    @Test
    fun postTest(){

        val userRequest = UserRequest().apply {
            this.name = "mose"
            this.age = 27
            this.phoneNumber = "010-1234-5678"
            this.address = "Seoul"
            this.createdAt = "1994-04-13 04:13:32"
            this.email = "km413.dev@gmail.com"
        }
        val json = jacksonObjectMapper().writeValueAsString(userRequest)
        println(json)

        mockMvc.perform(
                post("/api/exception")
                        .content(json)
                        .contentType("application/json")
                        .accept("application/json")
        ).andExpect(
                status().isOk
        ).andExpect(
                content().contentType("application/json")
        ).andExpect(
            jsonPath("\$.name").value("mose")
        ).andExpect(
                jsonPath("\$.age").value(27)
        ).andExpect(
                jsonPath("\$.email").value("km413.dev@gmail.com")
        ).andExpect(
                jsonPath("\$.address").value("Seoul")
        ).andExpect(
                jsonPath("\$.phoneNumber").value("010-1234-5678")
        ).andDo(print())
    }
    @Test
    fun postFailTest(){

        val userRequest = UserRequest().apply {
            this.name = "mose"
            this.age = -27
            this.phoneNumber = "0111-01234-5678"
            this.address = "Seoul"
            this.createdAt = "1994-04-13 04:13:32"
            this.email = "km413.dev@gmail.com"
        }
        val json = jacksonObjectMapper().writeValueAsString(userRequest)
        println(json)

        mockMvc.perform(
                post("/api/exception")
                        .content(json)
                        .contentType("application/json")
                        .accept("application/json")
        ).andExpect(
                status().isBadRequest
        ).andExpect(
                content().contentType("application/json")
        ).andDo(print())
    }
}