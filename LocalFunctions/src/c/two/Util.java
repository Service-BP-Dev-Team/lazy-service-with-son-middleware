package c.two;

import c.general.Offer;
import c.general.Request;

public class Util {

	public static Object makeOffer(Object request){
		
		return Offer.getBasicOffer();
	}
	
	public static Object notMeet(Object request, Object offer){
		//fileAdapt to serialize/deserialize and avoid cas problems link to the use of multiples classloader
		Offer offr =(Offer)c.general.Util.fileAdapt(offer);
		Request req = (Request)c.general.Util.fileAdapt(request);
		if(offr!=null && req!=null){
			return (offr.getPrice()>req.getMaxPrice());
		}
		return true;
		
	}
	
	public static Object initiateDelivery(Object offer){
		
		return "http://link-to-delivery-info";
	}
	
	public static Object nullValue(){
		
		return "null";
	}
	
	public static Boolean validationOkGuard(Object askValidation, Object validation, Object offer){
		
		if (validation!= null){
			if((Boolean) validation==true){
				return true;
			}
		}
		else if (askValidation==null && offer!=null){
			return true;
		}
		
		return false;
	}
	
	public static Boolean validationNotOkGuard(Object askValidation, Object validation, Object offer){
		
		return (!validationOkGuard(askValidation,validation,offer) && offer!=null);
	}
	
	
}
