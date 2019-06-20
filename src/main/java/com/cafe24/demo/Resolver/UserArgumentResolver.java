package com.cafe24.demo.Resolver;

import java.time.LocalDateTime;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.cafe24.demo.Annotation.SocialUser;
import com.cafe24.demo.DAO.UserDAO;
import com.cafe24.demo.VO.SocialType;
import com.cafe24.demo.VO.User;

import org.springframework.core.MethodParameter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    private UserDAO userDAO;

    public UserArgumentResolver(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    @Override
    public Object resolveArgument(MethodParameter arg0, ModelAndViewContainer arg1, NativeWebRequest arg2, WebDataBinderFactory arg3) throws Exception {

        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
        User user = (User) session.getAttribute("user");

        return getUser(user, session);
    }

    private Object getUser(User user, HttpSession session) {

        if( user == null){
            try{

                OAuth2AuthenticationToken authentication = (OAuth2AuthenticationToken) SecurityContextHolder.getContext()
                        .getAuthentication();

                Map<String, Object> map = authentication.getPrincipal().getAttributes();

                User convertUser = convertUser(authentication.getAuthorizedClientRegistrationId(), map);

                System.out.println(authentication.toString());
                

                if(userDAO.findByEmail(convertUser.getEmail()) == null){ user = userDAO.save(convertUser); }
                
                user = userDAO.findByEmail(convertUser.getEmail());
                
                setRoleIfNotSame(user, authentication, map);
                session.setAttribute("user", user);

            }catch(ClassCastException e){
                return user;
            }
        }
        return user;
    }

    private void setRoleIfNotSame(User user, OAuth2AuthenticationToken authentication, Map<String, Object> map) {
            
        if(!authentication.getAuthorities().contains( new SimpleGrantedAuthority(user.getSocialType().getRoleType())) ) {
                SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(map, "N/A",
                    AuthorityUtils.createAuthorityList(user.getSocialType().getRoleType())));
            }
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(SocialUser.class) != null &&
               parameter.getParameterType().equals(User.class);
    }

    private User convertUser(String authority, Map<String, Object> map){
        if(SocialType.GOOGLE.getValue().equals(authority)) return getModernUser(SocialType.GOOGLE, map);
        
        return null;
    }

    private User getModernUser(SocialType socialType, Map<String, Object> map) {
        return User.builder()
                    .name(String.valueOf(map.get("name")))
                    .email(String.valueOf(map.get("email")))
                    .principal(String.valueOf(map.get("id")))
                    .socialType(socialType)
                    .createdDate(LocalDateTime.now())
                    .build();
    }
}