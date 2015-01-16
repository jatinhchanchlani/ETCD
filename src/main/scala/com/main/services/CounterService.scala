package main.scala.com.main.services

import org.springframework.context.annotation.Configuration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.web.bind.annotation._
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestParam
import javax.validation.Valid
import org.springframework.validation.BindingResult
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import main.scala.com.model.User
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.validation._
import javax.servlet.http.HttpServletResponse
import com.justinsb.etcd.EtcdClient
import com.justinsb.etcd.EtcdResult;
import java.net.URI

@RestController
@RequestMapping(value= Array("/counter"))
class CounterService 
{
	

@RequestMapping(value= Array("/v1"),method=Array(RequestMethod.GET),
produces = Array("application/json"))
@ResponseBody 
@ResponseStatus(HttpStatus.OK)
def getCount() : String = 
	{
  
   //HttpURLConnection.setInstanceFollowRedirects(false);
  //curl -L http://54.67.103.220:4001/v2/keys/010000939/counter
  //var client : EtcdClient = EtcdClientFactory.newInstance("http://ETCD-ELB-1425118811.us-west-2.elb.amazonaws.com:4001/");
  /*var client : EtcdClient = EtcdClientFactory.newInstance("http://54.149.71.90:4001/");
  
  //var value : Int = Integer.parseInt(client.get("/count"));
  //value = value + 1;
  //var count_val : String = String.valueOf(value);
  
  client.set("/count", "hello");
    
    
 
  return ("Counter value from ETCD clusters: "+ client.get("/count") );
 
 */
var client : EtcdClient = new EtcdClient(URI.create("http://54.148.106.8:4001/"));

var key: String = "count";

var result : EtcdResult = client.get(key);  

var prev_value : String = result.node.value;println("vale from the server: " + prev_value);
var res: Int = Integer.parseInt(prev_value);
res = res + 1;
var value: String = String.valueOf(res);

result = client.set(key,value);

  
  
  
  return ("value from the etcd cluster: "+ value);
	}

		
		
}