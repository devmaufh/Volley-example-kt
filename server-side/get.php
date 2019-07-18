<?php
include_once('DATABASE/functions.php');
// required headers
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: GET");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
$response=array();
$data=selectTareas();
if(count($data)>0){
  http_response_code(200);
  echo json_encode($data);
}else{
  http_response_code(400);
  echo json_encode(array("message" => "No se encontro informacion"));
}
?>