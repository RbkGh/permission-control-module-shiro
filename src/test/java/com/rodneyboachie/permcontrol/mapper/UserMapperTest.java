package com.rodneyboachie.permcontrol.mapper;

import com.rodneyboachie.permcontrol.db.UserEntity;
import org.junit.Assert;

import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * author: acerbk
 * Date: 23/02/2022
 * Time: 2:27 am
 */
@RunWith(SpringRunner.class)
public class UserMapperTest {

    @Mock
    private UsersMapper userMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("$2a$12$s79MExz5XMA3xJjA6208oenKkTsnafRtl.0chxctQu.n.vP5soqJa");

        Mockito.when(this.userMapper.getUserByUsername("max"))
                .thenReturn(userEntity);

        UserEntity userEntityRolePerm = new UserEntity();
        userEntityRolePerm.setUserRole("buyer");
        userEntityRolePerm.setPermission("product:view");
        Mockito.when(this.userMapper.getUserRoleAndPermission("rodney"))
                .thenReturn(userEntityRolePerm);
    }

    @Test
    public void should_expect_user_in_db() {
        UserEntity userEntity = this.userMapper.getUserByUsername("max");

        Assert.assertTrue(userEntity.getPassword()
                .equalsIgnoreCase("$2a$12$s79MExz5XMA3xJjA6208oenKkTsnafRtl.0chxctQu.n.vP5soqJa"));
    }

    @Test
    public void should_expect_user_to_have_buyer_role_and_product_view_permission() {
        UserEntity userEntity = this.userMapper.getUserRoleAndPermission("rodney");

        Assert.assertTrue(userEntity.getUserRole()
                .equalsIgnoreCase("buyer"));
        Assert.assertTrue(userEntity.getPermission()
                .equalsIgnoreCase("product:view"));
    }
}
