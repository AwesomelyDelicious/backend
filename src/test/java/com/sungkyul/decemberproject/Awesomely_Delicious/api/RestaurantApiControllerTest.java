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
                                fieldWithPath("restaurant_name").type(JsonFieldType.STRING).description("육꼬"),
                                fieldWithPath("user_id").type(JsonFieldType.NUMBER).description("1"),
                                fieldWithPath("x").type(JsonFieldType.NUMBER).description("123.23"),
                                fieldWithPath("y").type(JsonFieldType.NUMBER).description("33.68"),
                                fieldWithPath("star_count").type(JsonFieldType.NUMBER).description("4.5"),
                                fieldWithPath("memo").type(JsonFieldType.STRING).description("맛있어요.")
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

        Long id = 1L;
        mvc.perform(
                        RestDocumentationRequestBuilders.delete("/api/v1/restaurant/")
                                .param("restaurant_id","1")
                )
                .andExpect(status().isOk())
                .andDo(
                        restDocs.document(
//                                pathParameters(
//                                        parameterWithName("restaurant_id").description("맛집아이디")
//                                )
                              requestParameters(parameterWithName("restaurant_id").description("1"))
                        )
                );
    }

    @Test
    void updateRestaurantV2() throws Exception{
        mvc.perform(
                patch("/api/v1/restaurant")
        )
                .andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                requestParameters(parameterWithName("restaurant_id").description("1")),
                                responseFields(
                                        fieldWithPath("star_count").type(JsonFieldType.NUMBER).description("4.8"),
                                        fieldWithPath("memo").type(JsonFieldType.STRING).description("너무 맛있어요.")
                                        )

                        )
                );
    }
}