package c.two;

import c.general.Offer;
import c.general.Request;

public class Util {

	public static Object makeOffer(Object request){
		
		return Offer.getBasicOffer();
	}
	
	public static Object notMeet(Object request, Object offer){
		Offer offr =(Offer)c.general.Util.fileAdapt(offer);
		Request req = (Request)c.general.Util.fileAdapt(request);
		return (offr.getPrice()>req.getMaxPrice());
		
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
