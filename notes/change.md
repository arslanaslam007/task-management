

### 23. Send Complete Object in Signup and Login Response

> send complete object to user on signup and login

update model
- AuthenticationResponse , extend with UserDetailDTO

change auth service
- set fields of UserDetailDTO in response for signup
- set fields of UserDetailDTO in response for login


### 24. Fix API Endpoint after JWT Authentication


> correct task api endpoint

introduce model
- UserTaskDTO , consists of fields userId and taskId , constructor(both)/setter/getter

add to task repository
- add method , findByUserId , receive userId , return list of tasks
- add method , findByIdAndUserId , receive userId , return task
- (findByIdAndUserId as task id is unique for each but joining it with user id so that user doesn't get someone else task)

change task api endpoint
- Delete , /api/user/task , request as ModelAttribute , UserTaskDTO , pass it to service
- Get Tasks , /api/user/task/{id} , request as Request Path , getAllTasks as getTasks , pass it to service , update service caller
- Get Task , /api/user/task/taskId , request as ModelAttribute , UserTaskDTO , getTask as getTask , pass it to service

correct task api service
- reorder as get methods, create methods, update methods, delete methods , helper methods
- remove findById
- rename , getAllTasks to getTasks , receive userId , update repository caller
- rename , getTask as getTask , receive UserTaskDTO , add repository caller
- delete task , receive UserTaskDTO , make it transactional , add/update repository caller
- persist task , update repository handler
- remove unused import

> api controller and service tests
- comment it as we would fix it later





