package ru.job4j.cinema.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс-фильтр используется для ограничения доступа пользователя
 * ко всем страницам веб-приложения кроме регистарции и входа,
 * пока не будет выполнена авторизация
 * всегда работает перед {@link SessionFilter}
 *
 * @author Artem Chernikov
 * @version 1.0
 * @since 26.02.2023
 */
@Component
@Order(1)
public class AuthorizationFilter extends HttpFilter {

    /**
     * Метод используется для проверки адреса по которому хочет пройти пользователь,
     * если этот адрес совпадает с ардесом покупки билетов и пользователь не аторизован, то пользователь
     * автоматически будет перенаправлен на страницу с авторизацией
     *
     * @param request  - запрос
     * @param response - ответ
     * @param chain    - фильтр
     * @throws IOException      - {@link IOException}
     * @throws ServletException - {@link ServletException}
     */
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        var uri = request.getRequestURI();
        var userLoggedIn = request.getSession().getAttribute("user") != null;
        if (uri.startsWith("/ticket") && !userLoggedIn) {
            var loginPageUrl = request.getContextPath() + "/users/login";
            response.sendRedirect(loginPageUrl);
            return;
        }
        chain.doFilter(request, response);
    }
}
