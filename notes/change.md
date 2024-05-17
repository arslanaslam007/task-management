

### 23. Send Complete Object in Signup and Login Response

> send complete object to user on signup and login

update model
- AuthenticationResponse , extend with UserDetailDTO
- add builder annotation for it

change auth service
- set fields of UserDetailDTO in response for signup
- set fields of UserDetailDTO in response for login







