package com.unittest;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.*;
import static org.hamcrest.Matchers.*;
import org.springframework.http.MediaType;

import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.entity.*;
import com.service.*;

public class TestController extends SpringTests {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
    @Autowired
    private UserfileService userfileService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void listfilesTest() throws Exception {
		    mockMvc.perform(get("/listfiles"))
		            .andExpect(status().isOk())
		            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		            .andExpect(jsonPath("$", hasSize(3)));
	}

	@Test
	public void starredfilesTest() throws Exception {
		    mockMvc.perform(get("/starredfiles"))
		            .andExpect(status().isOk())
		            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		            .andExpect(jsonPath("$", hasSize(2)));
	}
	
	@Test
	public void listfoldersTest() throws Exception {
		    mockMvc.perform(get("/allfolders"))
		            .andExpect(status().isOk())
		            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		            .andExpect(jsonPath("$", hasSize(4)));
	}
	
	@Test
	public void accountTest() throws Exception {
		    mockMvc.perform(get("/account"))
		            .andExpect(status().isOk())
		            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		            .andExpect(jsonPath("$", hasSize(1)));
	}
	
	@Test
	public void activityTest() throws Exception {
		    mockMvc.perform(get("/activity"))
		            .andExpect(status().isOk())
		            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		            .andExpect(jsonPath("$", hasSize(10)));
	}
	
	@Test
	public void listgroupsTest() throws Exception {
		    mockMvc.perform(get("/allgroups"))
		            .andExpect(status().isOk())
		            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		            .andExpect(jsonPath("$", hasSize(2)));
	}
	
	@Test
	public void signinTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/signin")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content("{\"username\" : \"anj@anj.com\", \"password\" : \"123\" }")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void signupTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/signup")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content("{\"firstName\" : \"Anj\", \"lastName\" : \"Pra\",\"username\" : \"ap@ap.com\", \"password\" : \"123\" }")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void logoutTest() throws Exception {
		mockMvc.perform(post("/logout"))
				.andExpect(status().isOk());
	}
	
	@Test
	public void createfolderTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/createfolder")
		        .contentType(MediaType.APPLICATION_JSON)
		        .content("{\"folderName\" : \"foldertest\"}")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

}
