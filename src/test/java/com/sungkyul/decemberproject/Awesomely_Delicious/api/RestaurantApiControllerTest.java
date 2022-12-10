package com.sungkyul.decemberproject.Awesomely_Delicious.api;

import com.sungkyul.decemberproject.Awesomely_Delicious.domain.Restaurant;
import com.sungkyul.decemberproject.Awesomely_Delicious.repository.RestaurantRepository;
import com.sungkyul.decemberproject.Awesomely_Delicious.service.RestaurantService;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;


import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RestaurantApiController.class)
class RestaurantApiControllerTest extends AbstractRestDocsTests {

    @MockBean
    protected  RestaurantService restaurantService;
    @MockBean
    protected RestaurantRepository restaurantRepository;

    @Test
    void addRestaurantV1() throws Exception{
        RestaurantForm form = new RestaurantForm();
        form.setRestaurant_name("육꼬");
        form.setMemo("음식이 맛있어요");
        form.setX(1251.31f);
        form.setY(15123.24f);
        form.setStar_count(4.5f);
        form.setUser_id(1L);

        mvc.perform(post("/api/v1/restaurant")
                        .content(objectMapper.writeValueAsString(form))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(restDocs.document(
                        requestFields(
                                fieldWithPath("restaurant_name").type(JsonFieldType.STRING).description("맛집 이름"),
                                fieldWithPath("user_id").type(JsonFieldType.NUMBER).description("유저 아이디"),
                                fieldWithPath("x").type(JsonFieldType.NUMBER).description("맛집 x좌표"),
                                fieldWithPath("y").type(JsonFieldType.NUMBER).description("맛집 y좌표"),
                                fieldWithPath("star_count").type(JsonFieldType.NUMBER).description("별점"),
                                fieldWithPath("memo").type(JsonFieldType.STRING).description("메모 내용")
                        )
                       )
                );
    }

    @Test
    void deleteRestaurantV1() throws Exception{
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName("육꼬");
        restaurant.setMemo("음식이 맛있어요");
        restaurant.setX(1251.31f);
        restaurant.setY(15123.24f);
        restaurant.setStarCount(4.5f);
        restaurant.setRestaurant_id(1L);
        restaurantRepository.save(restaurant);

        mvc.perform(
                        RestDocumentationRequestBuilders.delete("/api/v1/restaurant/")
                                .param("restaurant_id","1")
                )
                .andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                              requestParameters(parameterWithName("restaurant_id").description("맛집 아이디"))
                        )
                );
    }

    @Test
    void updateRestaurantV1() throws Exception{
        Restaurant restaurant = new Restaurant();
        restaurant.setRestaurantName("육꼬");
        restaurant.setMemo("음식이 맛있어요");
        restaurant.setX(1251.31f);
        restaurant.setY(15123.24f);
        restaurant.setStarCount(4.5f);
        restaurant.setRestaurant_id(1L);
        restaurantRepository.save(restaurant);

        UpdateRestaurantForm form = new UpdateRestaurantForm();
        form.setMemo("양이 많아요");
        form.setStar_count(4.0f);

        mvc.perform(
                        RestDocumentationRequestBuilders.patch("/api/v1/restaurant/")
                                .param("restaurant_id","1")
                                .content(objectMapper.writeValueAsString(form))
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                requestParameters(parameterWithName("restaurant_id").description("맛집 아이디")),
                                requestFields(
                                        fieldWithPath("star_count").type(JsonFieldType.NUMBER).description("수정할 별점"),
                                        fieldWithPath("memo").type(JsonFieldType.STRING).description("수정할 메모 내용")
                                )
                        )
                );
    }
}