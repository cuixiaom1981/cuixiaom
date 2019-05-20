package  com.example.demo.security;
import com.example.demo.entity.JwtUser;
import com.example.demo.entity.LoginUser;
import com.example.demo.identify.face;
import com.example.demo.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	
    private ThreadLocal<Integer> rememberMe = new ThreadLocal<>();
    private AuthenticationManager authenticationManager;
    private LoginUser loginUser;


   
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/user/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
            HttpServletResponse response) throws AuthenticationException {

// 从输入流中获取到登录的信息
try {
	
   loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);
  
//rememberMe.set(loginUser.getRememberMe());{
 
  return authenticationManager.authenticate(
  new UsernamePasswordAuthenticationToken(loginUser.getName(), loginUser.getPassword(), new ArrayList<>())
);
//  }else {response.setStatus(407);return null;}
} catch (IOException e) {
e.printStackTrace();
return null;
}
}


    // 成功验证后调用的方法
    // 如果验证成功，就生成token并返回
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
    	String role = "";
        JwtUser jwtUser = (JwtUser) authResult.getPrincipal();
        System.out.println("jwtUser:" + jwtUser.toString());
       // boolean isRemember = rememberMe.get() == 1;
     
        Collection<? extends GrantedAuthority> authorities = jwtUser.getAuthorities();
        for (GrantedAuthority authority : authorities){
            role = authority.getAuthority();
        }
        
     if (face.faceId(loginUser)) {
     //   String token = JwtUtils.createToken(jwtUser.getUsername(), role, isRemember);
        String token = JwtUtils.createToken(jwtUser.getUsername(),role, false);
        // 返回创建成功的token
        // 但是这里创建的token只是单纯的token
        // 按照jwt的规定，最后请求的时候应该是 `Bearer token`
        response.setHeader("token", JwtUtils.TOKEN_PREFIX + token);
        }
     else {response.setStatus(407);};
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.getWriter().write("authentication failed, reason: " + failed.getMessage());
    }
}
