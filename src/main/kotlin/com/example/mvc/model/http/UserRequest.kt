package com.example.mvc.model.http

import com.example.mvc.annotation.StringFormatDateTime
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.*

//@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class UserRequest(
        @field:NotEmpty
        @field:Size(min = 2, max = 8, message = "네이밍 에러 메시지 ")
        var name :String? = null,

        @field:PositiveOrZero // 0 <= 숫자 검증
        var age:Int? =null,

        @field:Email  // email 양식 검증
        var email:String?=null,

        @field:NotBlank // 공백 검증
        var address:String?=null,

        var occupation:String?=null,


        @field:Pattern(regexp = "\\d{2,3}-\\d{3,4}-\\d{4}\$") // 정규식 검증
        var phoneNumber:String?=null,

        @field:StringFormatDateTime(pattern = "yyyy-MM-dd HH:mm:ss", message = "패턴이 올바르지 않습니다.")
        var createdAt:String?=null // yyyy-MM-dd HH:mm:ss
){

//        @AssertTrue(message = "생성일자의 패턴은 yyyy-MM-dd HH:mm:ss")
//        private fun isValidCreatedAt():Boolean{
//                // 정상 true, 비정상 false
//                return try{
//                        LocalDateTime.parse(this.createdAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
//                        true
//                }catch (e:Exception){
//                        false
//                }
//        }
}
