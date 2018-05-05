package com.nong.nongo2o.entity.request;import com.nong.nongo2o.entity.base.Request;import java.util.Date;/** * @author wisdom * @date: 2017-07-18 21:20:30 * @since V1.0 */public class CreateUserRequest extends Request {    private String openId;    /**     * 用户名称     */    private String userName;    /**     * 用户昵称     */    private String userNick;    /**     * 用户状态 0-invalid  1-正常     */    private Integer userStatus;    /**     * 用户类型： 0-用户 1-商家     */    private Integer userType;    /**     * 头像     */    private String avatar;    /**     * 电话号码     */    private String phone;    /**     * 邮箱     */    private String email;    /**     * 性别： 0-男 1-女     */    private Integer sex;    /**     * 用户密码     */    private String password;    /**     * 个人简介     */    private String profile;    /**     * 所属地区     */    private String location;    /**     * 身份证号码     */    private String idNumber;    /**     * 身份证正面照片     */    private String idFront;    /**     * 身份证反面照片     */    private String idBack;    /**     * 邀请码     */    private String inviteCode;    /**     * 邀请者【userCode】     */    private String inviter;    private String validCode;    private String consigneeAddress;    public String getOpenId() {        return openId;    }    public void setOpenId(String openId) {        this.openId = openId;    }    public String getValidCode() {        return validCode;    }    public void setValidCode(String validCode) {        this.validCode = validCode;    }    public String getUserName() {        return this.userName;    }    public void setUserName(String userName) {        this.userName = userName;    }    public String getUserNick() {        return this.userNick;    }    public void setUserNick(String userNick) {        this.userNick = userNick;    }    public Integer getUserStatus() {        return this.userStatus;    }    public void setUserStatus(Integer userStatus) {        this.userStatus = userStatus;    }    public Integer getUserType() {        return this.userType;    }    public void setUserType(Integer userType) {        this.userType = userType;    }    public String getAvatar() {        return this.avatar;    }    public void setAvatar(String avatar) {        this.avatar = avatar;    }    public String getPhone() {        return this.phone;    }    public void setPhone(String phone) {        this.phone = phone;    }    public String getEmail() {        return this.email;    }    public void setEmail(String email) {        this.email = email;    }    public Integer getSex() {        return this.sex;    }    public void setSex(Integer sex) {        this.sex = sex;    }    public String getPassword() {        return this.password;    }    public void setPassword(String password) {        this.password = password;    }    public String getProfile() {        return this.profile;    }    public void setProfile(String profile) {        this.profile = profile;    }    public String getLocation() {        return this.location;    }    public void setLocation(String location) {        this.location = location;    }    public String getIdNumber() {        return this.idNumber;    }    public void setIdNumber(String idNumber) {        this.idNumber = idNumber;    }    public String getIdFront() {        return this.idFront;    }    public void setIdFront(String idFront) {        this.idFront = idFront;    }    public String getIdBack() {        return this.idBack;    }    public void setIdBack(String idBack) {        this.idBack = idBack;    }    public String getInviteCode() {        return inviteCode;    }    public void setInviteCode(String inviteCode) {        this.inviteCode = inviteCode;    }    public String getInviter() {        return inviter;    }    public void setInviter(String inviter) {        this.inviter = inviter;    }    public String getConsigneeAddress() {        return consigneeAddress;    }    public void setConsigneeAddress(String consigneeAddress) {        this.consigneeAddress = consigneeAddress;    }}