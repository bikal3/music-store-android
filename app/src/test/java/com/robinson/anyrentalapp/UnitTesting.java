package com.robinson.anyrentalapp;

import com.robinson.anyrentalapp.BLL.CartBLL;
import com.robinson.anyrentalapp.BLL.UserBLL;
import com.robinson.anyrentalapp.Model.User;
import com.robinson.anyrentalapp.Response.CartResponse;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UnitTesting {
    private UserBLL userBLL;
    private CartBLL cartBLL;

    @Before
    public void setup() {
        userBLL = new UserBLL();
    }

    @Test
    public void testA_emptyvalue_signup() {
        User user = new User("", "Test", "unittest@example.com", "password");
        boolean signedUp = userBLL.registerUser(user);
        assertFalse(signedUp);
    }

    @Test
    public void testB_validinput_signup() {
        User user = new User("Unit", "Testing", "unittest@example.com", "password");
        boolean signedUp = userBLL.registerUser(user);
        assertTrue(signedUp);
    }
    @Test
    public void testC_emptyvalue_signin() {

        User userlogin = userBLL.loginUser("", "password");

        assertNull(userlogin);
    }
    @Test
    public void testD__validinput_signin() {

        User userlogin = userBLL.loginUser("b@b.com", "password");

        assertEquals("b@b.com", userlogin.getEmail());
    }
    @Test
    public void testE__addtocart() {
        String userid="5e3b6f4b57223c52cc894ff5";
        String prodid="5e3247887e5de91735f4a58f";
        boolean cart = cartBLL.addCart(prodid,userid);
        assertTrue(cart);

    }

}
