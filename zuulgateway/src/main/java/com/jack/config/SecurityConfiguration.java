package com.jack.config;

import com.jack.filter.MyFilter;
import com.jack.pojo.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

/**
 * Created by jack on 2018/1/18.
 * spring security 配置
 * https://www.jianshu.com/p/e6655328b211
 *
 * https://www.jianshu.com/p/4468a2fff879
 *
 * http://www.spring4all.com/article/428
 *
 * http://doc.spring4all.com/spring-guildes/spring-security-architecture.html
 *
 */

/**
 *
 认证用户身份的请求:
 xxxFilter -> AuthenticationManager -> AuthenticationProvider -> UserDetailService -> UserDetails

 获取用户认证信息的请求:
 SecurityContextHolder -> SecurityContextHolderStrategy ->SecurityContext -> Authentication
 *
 */
@Configuration
//@Order
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyFilter myFilter;

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        //http.authorizeRequests()其中这里的意思是指通过authorizeRequests()方法来开始请求权限配置。
        http.authorizeRequests()
                //我们指定任何用户都可以访问多个URL的模式。
                //任何用户都可以访问以"/resources/","/signup", 或者 "/about"开头的URL。
                .antMatchers("/resources/**", "/signup", "/about").permitAll()
               // .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()//处理跨域请求中的Preflight请求
                //以 "/admin/" 开头的URL只能让拥有 "ROLE_ADMIN"角色的用户访问。请注意我们使用 hasRole 方法，没有使用 "ROLE_" 前缀。
                .antMatchers("/admin/**").hasRole("ADMIN")
                //任何以"/db/" 开头的URL需要同时具有 "ROLE_ADMIN" 和 "ROLE_DBA"权限的用户才可以访问。和上面一样我们的 hasRole 方法也没有使用 "ROLE_" 前缀。
                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
                //任何以"/db/" 开头的URL只需要拥有 "ROLE_ADMIN" 和 "ROLE_DBA"其中一个权限的用户才可以访问。和上面一样我们的 hasRole 方法也没有使用 "ROLE_" 前缀。
                .antMatchers("/db/**").hasAnyRole("ADMIN", "DBA")
                //尚未匹配的任何URL都要求用户进行身份验证,anyRequest().authenticated()是对http所有的请求必须通过授权认证才可以访问
                .anyRequest().authenticated()
                //and()返回HttpSecurity
                .and()
                //这个formLogin().permitAll()方法允许所有用户基于表单登录访问/login这个page。
                .formLogin()
                //loginPage()指定登录页的路径,permitAll()必须允许所有用户访问我们的登录页（例如未验证的用户，否则验证流程就会进入死循环）
                //注意：这个自定义表单登录的自定义页面中的登录名参数必须被命名为username密码参数必须被命名为password
                //.loginPage("/page/test")
                //指定登录成功后跳转到/index页面
                //.usernameParameter("username")//自定义用户名参数名称
                //.passwordParameter("password")//自定义密码参数名称
                .defaultSuccessUrl("/page/wx")
                //指定登录失败后跳转到/login?error页面
                .failureUrl("/page/fail")
                .permitAll()
                .and()
                //开启cookie储存用户信息，并设置有效期为30分钟，参数是秒为单位，指定cookie中的密钥
                .rememberMe().tokenValiditySeconds(30*60).key("jack")
                .and()
                .logout()
                //指定登出的url
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .permitAll();
        //关闭跨域保护
        http.csrf().disable();
        //在 beforeFilter 之前添加 filter
        http.addFilterBefore(myFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 重写了configure参数为AuthenticationManagerBuilder的方法
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        //并根据传入的AuthenticationManagerBuilder中的userDetailsService方法来接收我们自定义的认证方法。
        //且该方法必须要实现UserDetailsService这个接口。
        //auth.userDetailsService(new myUserDetailsService())
                //密码使用BCryptPasswordEncoder()方法验证，因为这里使用了BCryptPasswordEncoder()方法验证。
        // 所以在注册用户的时候在接收前台明文密码之后也需要使用BCryptPasswordEncoder().encode(明文密码)方法加密密码。
               // .passwordEncoder(new BCryptPasswordEncoder());
        //auth.userDetailsService(new MyUserDetailsService()).passwordEncoder(new Md5PasswordEncoder());
        auth.userDetailsService(new MyUserDetailsService());//.passwordEncoder(new BCryptPasswordEncoder());
        /*auth.inMemoryAuthentication().withUser("jack").password("123456").roles("user")
                .and().withUser("jack1").password("123456").roles("user")
                .passwordEncoder(new Md5PasswordEncoder());*/

    }

    /*说明：
    HttpSecurity 有三个常用方法来配置：

    addFilterBefore(Filter filter, Class<? extends Filter> beforeFilter)
    在 beforeFilter 之前添加 filter
    addFilterAfter(Filter filter, Class<? extends Filter> afterFilter)
    在 afterFilter 之后添加 filter
    addFilterAt(Filter filter, Class<? extends Filter> atFilter)
    在 atFilter 相同位置添加 filter， 此 filter 不覆盖 filter
    */
}
