/**
 * 
 */
function Validator(){
	var instantiated=null;
	
	var init=function(){
		return{
			validate:function($textbox){
				$textbox.keydown(function(event) {
			        // Allow: backspace, delete, tab, escape, enter and .
			        if ( $.inArray(event.keyCode,[46,8,9,27,13,190]) !== -1 ||
			             // Allow: Ctrl+A
			            (event.keyCode == 65 && event.ctrlKey === true) || 
			             // Allow: home, end, left, right
			            (event.keyCode >= 35 && event.keyCode <= 39)) {
			                 // let it happen, don't do anything
			                 return;
			        }
			        else {
			            // Ensure that it is a number and stop the keypress
			            if (event.shiftKey || (event.keyCode < 48 || event.keyCode > 57) && (event.keyCode < 96 || event.keyCode > 105 )) {
			                event.preventDefault(); 
			            }   
			        }
			    });
			}
		}
		
		
	}
	
	return{
		getInstance:function(){
			
			if(!instantiated)
				instantiated=init();
			
			return instantiated;
		}
	}
}