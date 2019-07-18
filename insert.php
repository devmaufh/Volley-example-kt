<?php
include_once('DATABASE/functions.php');

// required headers
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
$data = json_decode(file_get_contents("php://input"));
if(
    !empty($data->titulo) &&
    !empty($data->descripcion)
  ){
    $res=addTarea(array(
      'titulo'=>$data->titulo,
      'descripcion'=>$data->descripcion,
    ));
    $response=array();
    if(is_int($res)){
      $response['message']="Registro insertado";
      $response['id']=$res;
      $code=200;
    }else{
      $response['message']="Error al insertar";
      $code=400;
    }
    http_response_code($code);
    echo json_encode($response);
}
else{
     http_response_code(400);
     echo json_encode(array("message" => "Parametros incorrectos"));
}
?>