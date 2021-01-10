<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GoogleSearch</title>
</head>
<body>
 <form action='${requestUri}' method='get'>
  <div class="bg">
   <img
    src="https://youimg1.tripcdn.com/target/100110000000qfs9mE3E1_D_1180_558.jpg ">
  </div>
  <div style="top: 430px; left: 200px; position: absolute;">
  <h1><b><em><center>Travel Japan</center></em></b></h1>
   <input type='text' name='keyword' placeholder='keyword'
    style="width: 500px";"height: 40px" /> <input type='submit'
    value='submit' />
  </div>
 </form>
</body>
<style>
.bg {
 position: fixed;
 top: 0;
 left: 0;
 bottom: 0;
 right: 0;
 z-index: -999;
}

.bg img {
 min-height: 100%;
 min-width: 1000px;
 width: 100%;
}

@media screen and (max-width: 1000px) {
 img.bg {
  left: 50%;
  margin-left: -500px;
 }
}
</style>
</html>