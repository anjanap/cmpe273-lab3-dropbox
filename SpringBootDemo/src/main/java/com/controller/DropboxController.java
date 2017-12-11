package com.controller;

import com.entity.*;
import com.service.*;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.w3c.dom.html.HTMLParagraphElement;

import java.util.*;
import java.io.*;
import java.nio.file.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller    
@CrossOrigin(origins = "http://localhost:3000")

public class DropboxController {
    @Autowired
    private UserService userService;
    
    @Autowired
    private FolderService folderService;
    
    @Autowired
    private ActivityService activityService;
    
    @Autowired
    private UserfileService userfileService;

    @Autowired
    private GroupService groupService;

    @Autowired 
    private HttpSession session;

    //SIGNUP
    @PostMapping(path="/signup",consumes = MediaType.APPLICATION_JSON_VALUE) 
    public  ResponseEntity<?> addNewUser (@RequestBody User user) {
        userService.addUser(user);
        System.out.println("Saved: "+user.getEmail());
        File file = new File("/Users/anjana/Downloads/SpringBootDemoCode/"+user.getEmail());
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
        return new ResponseEntity(1,HttpStatus.CREATED);
    }
    
    
    //SIGNIN
    @PostMapping(path="/signin",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody String user){
        JSONObject jsonObject = new JSONObject(user);
        List <User> details=userService.login(jsonObject.getString("username"),jsonObject.getString("password"));
        session.setAttribute("username",jsonObject.getString("username"));
        session.setAttribute("userid",details.get(0).getuserID());
        System.out.println("Userid: "+session.getAttribute("userid")+" Username: "+session.getAttribute("username"));   
        return new ResponseEntity(userService.login(jsonObject.getString("username"),jsonObject.getString("password")),HttpStatus.OK);
    }
    
    
    //LOGOUT
    @PostMapping(value = "/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> logout(HttpSession session) {
        System.out.println(session.getAttribute("username"));
        session.invalidate();
        return  new ResponseEntity(HttpStatus.OK);
    }
    
    
    //ACCOUNT DETAILS
    @GetMapping(path="/account",produces = MediaType.APPLICATION_JSON_VALUE)
     public @ResponseBody Iterable<User> getAllUsers() {
 	   Integer uid=Integer.parseInt((session.getAttribute("userid")).toString());
 	   List<User> useracc = new ArrayList<User>();
 	   List<User> fetchacc = userService.getAllUsers();
 	   System.out.println("CHECK SIZE: "+fetchacc.size());
 	   for(int i=0;i<fetchacc.size();i++) {
 		   if(fetchacc.get(i).getuserID() == uid)
 			  useracc.add(fetchacc.get(i));
 	   }
 	   return useracc;
     }
    
    
    //CREATE NEW FOLDER
    @PostMapping(path="/createfolder",consumes = MediaType.APPLICATION_JSON_VALUE) 
    public  ResponseEntity<?> addNewUser (@RequestBody Folder folder) {
    	System.out.println("Saved: "+folder.getFolderName());
    	Integer uid=Integer.parseInt((session.getAttribute("userid")).toString());
    	folder.setUserID(uid);
    	//folder.setFolderName(folder.);;
     folderService.addFolder(folder);
        Activity newact=new Activity(uid,folder.getFolderName()+" : Folder created");
        activityService.addActivity(newact);
        return new ResponseEntity(1,HttpStatus.CREATED);
    }
    
    
   //LIST ALL FOLDERS
   @GetMapping(path="/allfolders",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<Folder> getAllFolders() {
	   Integer uid=Integer.parseInt((session.getAttribute("userid")).toString());
	   List<Folder> userfiles = new ArrayList<Folder>();
	   List<Folder> fetchfiles = folderService.getAllFolders();
	   for(int i=0;i<fetchfiles.size();i++) {
		   if(fetchfiles.get(i).getUserID() == uid)
			   userfiles.add(fetchfiles.get(i));
	   }
	   return userfiles;
    }
   

    //UPLOAD FILE
    @PostMapping(path="/addfile")
	public ResponseEntity<?> fileUpload(@RequestBody MultipartFile file) {
    	String n=(session.getAttribute("username")).toString();
    	String UPLOAD_FOLDER = "/Users/anjana/Downloads/SpringBootDemoCode/"+n+"/";
    	System.out.println("API SUCCESS---------"+UPLOAD_FOLDER);
		if (file.isEmpty()) {
			System.out.println("****Empty file: "+session.getAttribute("username"));
			return new ResponseEntity(0,HttpStatus.NOT_FOUND);
		}
		try {
			System.out.println("****TRY file: "+file.getOriginalFilename());
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
			int uid=Integer.parseInt((session.getAttribute("userid")).toString());
			Userfile newfile=new Userfile(uid,file.getOriginalFilename(),1,0,0);
			userfileService.addFile(newfile);
	        Activity newact=new Activity(uid,file.getOriginalFilename()+" : File uploaded");
	        activityService.addActivity(newact);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity(1,HttpStatus.CREATED);
	}
    
    
    //UPLOAD FILE TO FOLDER
    @PostMapping(path="/folderfile")
	public ResponseEntity<?> folderfileUpload(@RequestBody MultipartFile file, Integer foldID) {
    	String n=(session.getAttribute("username")).toString();
    	String UPLOAD_FOLDER = "/Users/anjana/Downloads/SpringBootDemoCode/"+n+"/";
    	System.out.println("CHECK FID: "+foldID);
		if (file.isEmpty()) {
			return new ResponseEntity(0,HttpStatus.NOT_FOUND);
		}
		try {
			System.out.println("****TRY file: "+file.getOriginalFilename());
			byte[] bytes = file.getBytes();
			Path path = Paths.get(UPLOAD_FOLDER + file.getOriginalFilename());
			Files.write(path, bytes);
			int uid=Integer.parseInt((session.getAttribute("userid")).toString());
			Userfile newfile=new Userfile(uid,file.getOriginalFilename(),0,foldID,0);
			userfileService.addFile(newfile);
	        Activity newact=new Activity(uid,file.getOriginalFilename()+" : File uploaded");
	        activityService.addActivity(newact);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity(1,HttpStatus.CREATED);
	}
    
    
    //LIST FILES
    @GetMapping(path="/listfiles",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<Userfile> getFileListing() {
	   Integer uid=18;//Integer.parseInt((session.getAttribute("userid")).toString());
	   List<Userfile> userfiles = new ArrayList<Userfile>();
	   List<Userfile> fetchfiles = userfileService.getAllFiles();
	   for(int i=0;i<fetchfiles.size();i++) {
		   if(fetchfiles.get(i).getUserID() == uid)
			   userfiles.add(fetchfiles.get(i));
	   }
	   return userfiles;
    }
    
    
    //DOWNLOAD FILES
    @RequestMapping(value="/download",method=RequestMethod.GET)
    public void downloadFile(@RequestParam("fname") String fname,HttpServletResponse response) throws IOException
    {
    	String n=(session.getAttribute("username")).toString();   	
    	String filePath = "/Users/anjana/Downloads/SpringBootDemoCode/"+n+"/"+fname;
    	System.out.println("Downlaod file path: "+filePath);
        String filePathToBeServed = filePath;
        File fileToDownload = new File(filePathToBeServed);
        InputStream inputStream = new FileInputStream(fileToDownload);
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment; filename="+fname); 
        IOUtils.copy(inputStream, response.getOutputStream());
        response.flushBuffer();
        inputStream.close(); 	
    }
    
    
    //DELETE FILE
    @PostMapping(path="/deletefile",consumes = MediaType.APPLICATION_JSON_VALUE) 
    public @ResponseBody ResponseEntity<?> deleteFile(@RequestBody Userfile f) {
	   userfileService.deleteFile(f.getFileID());
	   return new ResponseEntity(1,HttpStatus.OK);
    }
    
    
    //STAR FILE
    @PostMapping(path="/starfile",consumes = MediaType.APPLICATION_JSON_VALUE) 
    public @ResponseBody ResponseEntity<?> starFile(@RequestBody Userfile f) {
    	System.out.println("CHECK ID: "+f.getFileID());
	   if(f.getStarred()==0)
		   userfileService.updateStar(1,f.getFileID());
	   else if(f.getStarred()==1)
		   userfileService.updateStar(0,f.getFileID());
	   return new ResponseEntity(1,HttpStatus.OK);
    }
    
    
    //LIST STAR FILES
    @GetMapping(path="/starredfiles",produces = MediaType.APPLICATION_JSON_VALUE)
     public @ResponseBody Iterable<Userfile> allStarred() {
 	   Integer uid=Integer.parseInt((session.getAttribute("userid")).toString());
 	   List<Userfile> starred = new ArrayList<Userfile>();
 	   List<Userfile> fetchstarred = userfileService.getAllFiles();
 	   for(int i=0;i<fetchstarred.size();i++) {
 		   if(fetchstarred.get(i).getUserID() == uid && fetchstarred.get(i).getStarred()==1)
 			  starred.add(fetchstarred.get(i));
 	   }
 	   return starred;
     }
    
    
    //LIST USER ACTIVITIES
    @GetMapping(path="/activity",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<Activity> getActivities() {
	   Integer uid=Integer.parseInt((session.getAttribute("userid")).toString());
	   List<Activity> useractivities = new ArrayList<Activity>();
	   List<Activity> fetchact = activityService.getAllActivity();
	   for(int i=0;i<fetchact.size();i++) {
		   if(fetchact.get(i).getUserID() == uid)
			   useractivities.add(fetchact.get(i));
	   }
	   return useractivities;
    }
    
    
    //CREATE NEW GROUP
    @PostMapping(path="/creategroup",consumes = MediaType.APPLICATION_JSON_VALUE) 
    public  ResponseEntity<?> createGroup (@RequestBody Groups group) {
    	Integer uid=Integer.parseInt((session.getAttribute("userid")).toString());
    	group.setUserID(uid);
     groupService.addGroup(group);
      Activity newact=new Activity(uid,group.getGroupname()+" : Group created");
      activityService.addActivity(newact);
        return new ResponseEntity(1,HttpStatus.CREATED);
    }
    
    
    //LIST ALL GROUPS
    @GetMapping(path="/allgroups",produces = MediaType.APPLICATION_JSON_VALUE)
     public @ResponseBody Iterable<Groups> allGroups() {
 	   Integer uid=Integer.parseInt((session.getAttribute("userid")).toString());
 	   List<Groups> usergroups = new ArrayList<Groups>();
 	   List<Groups> fetchgroups = groupService.getAllGroups();
 	   for(int i=0;i<fetchgroups.size();i++) {
 		   if(fetchgroups.get(i).getUserID() == uid)
 			  usergroups.add(fetchgroups.get(i));
 	   }
 	   return usergroups;
     }
    
}