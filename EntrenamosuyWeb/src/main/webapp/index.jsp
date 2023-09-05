<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- 
  Added tailwind for inline styles, the documentation and playground: 
  	- https://play.tailwindcss.com/
  	- https://tailwindcss.com/docs 
  -->
<script src="https://cdn.tailwindcss.com"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@include file="/header.jsp" %>
</head>
<body class="bg-slate-800">
<main class="flex flex-col">
<div>
	<h1 class="text-white text-center bg-black sm:text-xl md:text-4xl">Some tailwind</h1>
</div>
	<div class="flex items-strech items-center justify-center">
	<button class="py-2 px-4 bg-blue-500 text-white font-semibold rounded-lg shadow-md hover:bg-violet-600 focus:outline-none focus:ring-2 focus:ring-blue-400 focus:ring-opacity-75">
  Save changes
</button>
</div>
</main>
</body>
</html>