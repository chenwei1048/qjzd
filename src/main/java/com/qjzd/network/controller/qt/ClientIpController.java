package com.qjzd.network.controller.qt;

import com.qjzd.network.result.CodeMsg;
import com.qjzd.network.result.Result;
import com.qjzd.network.vo.ClientIpVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @Author:
 * @Description:
 * @Date Create on 17:18 2018/11/20
 * @MOdifyBy:
 * @parameter
 */
@Controller
@RequestMapping("/client")
public class ClientIpController {

    @ResponseBody
    @RequestMapping("/addCount")
    public Result addCount(){
        return Result.success(null);
    }


    @ResponseBody
    @RequestMapping("/isSaveIp")
    public Result ip(HttpSession session, ClientIpVo clientIpVo){
        if(session.getAttribute("clientIp")==null){
            if(clientIpVo!=null){
                session.setAttribute("clientIp",clientIpVo);
            }
            return Result.error(CodeMsg.IP_EMPTY);
        }
        return Result.success(null);
    }
}
