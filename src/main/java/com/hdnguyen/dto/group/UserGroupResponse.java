package com.hdnguyen.dto.group;

import com.hdnguyen.entity.User;
import com.hdnguyen.entity.UserGroup;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
public class UserGroupResponse {
    private Long id; // idUserGroup
    private String email;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private Date createAt;
    private Boolean isEnabled;
    private List<String> roles;
    private String avatar;
    private String gender;
    private String phone;
    private Integer age;
    public UserGroupResponse(UserGroup userGroup) {

        id = userGroup.getId();

        roles = new ArrayList<>();
        avatar = userGroup.getUser().getAvatar();
        email = userGroup.getUser().getEmail();
        firstName = userGroup.getUser().getFirstName();
        lastName = userGroup.getUser().getLastName();
        dateOfBirth = userGroup.getUser().getDateOfBirth();
        createAt = userGroup.getUser().getCreateAt();
        isEnabled = userGroup.getUser().getIsEnabled();
        gender = userGroup.getUser().getGender();
        phone = userGroup.getUser().getPhone();
        age= userGroup.getUser().getAge();

        userGroup.getUser().getRoles().forEach(role -> {
            roles.add(role.getName());
        });
    }
}
