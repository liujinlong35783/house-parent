package com.house.pcontroller;

import com.github.pagehelper.PageInfo;
import com.house.pojo.House;
import com.house.pojo.Users;
import com.house.service.HouseService;
import com.house.utils.Page;
import com.house.utils.SearchSome;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by HP on 2019/12/27.
 */
@Controller("houseController2")
@RequestMapping("/page/")
public class HouseDataController {
    @Autowired
    private HouseService houseService;
    @RequestMapping("addHouse")
    public String addHouse(House house, ServletRequest request,
                           @RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile,
                           HttpSession session){
        try {
            String fname=pfile.getOriginalFilename();//获取文件名
            String fexname = fname.substring(fname.lastIndexOf("."));//文件后缀
            String saveFname = UUID.randomUUID() + fexname;//生成新的文件名
            String filePath="F:\\imgs\\"+saveFname;
            File file = new File(filePath);//文件路径
            pfile.transferTo(file);//上传
            String time = System.currentTimeMillis() + "";//系统毫秒值
            Integer id = ((Users) session.getAttribute("users")).getId();//获取session中的用户id
            //以下三条数据发布表单中获取不到,进行添加
            house.setUserId(id);//设置租房系统中的用户Id
            house.setId(time);//设置租房系统表的主键利用系统毫秒值 不唯一
            house.setPath(saveFname);//设置文件图片上传路径
            int b = houseService.addHouse(house);
            if(b>0){
                request.setAttribute("addHouse","success");
            }else{
                request.setAttribute("addHouse","fail");
            }
            return "/page/fabu";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
//    @RequestParam(defaultValue = "1") int page,int rows,
    @RequestMapping("getAllHouseDataById")
    public String getAllHouseDataById( Page page,
                                      HttpSession session,Model model
                                      ){
        //设置页大小   选择设置默认值
        page.setRows(5);
        Users users = (Users) session.getAttribute("users");
        PageInfo<House> pageInfo = houseService.findAllHouseById(users.getId(), page);
        model.addAttribute("pageInfo",pageInfo);
        return "/page/guanli";
    }
    @RequestMapping("updateHouseUI")//回显
    public String updateHouseUI(String id,Model model){
        House house = houseService.getHouseById(id);
        model.addAttribute("house",house);
        return "/page/updata";
    }
    @RequestMapping("updateHouse")
    public String updateHouse(House house,Model model,String oldPicPath,
                              @RequestParam(defaultValue = "pfile") CommonsMultipartFile pfile){
        try {
            if (pfile.getOriginalFilename().equals("")){
                house.setPath(oldPicPath);
            }else{
                String fname=pfile.getOriginalFilename();//获取文件名
                String fexname = fname.substring(fname.lastIndexOf("."));//文件后缀
                String saveFname = UUID.randomUUID() + fexname;//生成新的文件名
                String filePath="F:\\imgs\\"+saveFname;
                File file = new File(filePath);//文件路径
                pfile.transferTo(file);//上传
                new File("F:\\imgs\\"+oldPicPath).delete();//删除原来文件
                house.setPath(saveFname);//设置文件图片上传路径
            }
            houseService.updataHouse(house);
            return "redirect:getAllHouseDataById";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
    @RequestMapping("delHouse")
    public String delHouse(String id){
        //此处的"1" 逻辑删除
        int b = houseService.delHouseById(id, 1);
        return "redirect:getAllHouseDataById";
    }
    @RequestMapping("getAllWithList")
    public String getAllWithList(SearchSome searchSome,Model model){
        searchSome.setRows(5);
        PageInfo<House> pageInfo = houseService.getAllHouseWithList(searchSome);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("searchSome",searchSome);
        return "/page/list";
    }
}
