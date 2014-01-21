/**
 * 
 */
MenuAction={};

MenuAction.stateCount=0;

MenuAction.menuInit=(function(){
	$('.btn-del').attr("disabled",true);
	$('.btn-moveto').attr("disabled",true);
})();

MenuAction.toggleMenuState=function(value){
	$('.btn-del').attr("disabled",value);
	$('.btn-moveto').attr("disabled",value);
}

MenuAction.enquireMenuState=function(){
	if(MenuAction.stateCount>0)
		MenuAction.toggleMenuState(false);
	else
		MenuAction.toggleMenuState(true);
}

MenuAction.toggleSelect=function(value){
	if(value){
		MenuAction.stateCount++;
		MenuAction.enquireMenuState();
	}
	else{
		MenuAction.stateCount--;
		MenuAction.enquireMenuState();
	}
}

MenuAction.selectAll=function(){

	$('#contacts .chk').each(function(index,el){
		MenuAction.stateCount++;
		$(this).prop("checked",true);
	});
	
	MenuAction.toggleMenuState(false);
}

MenuAction.selectNone=function(){

	$('#contacts .chk').prop("checked",false);
	MenuAction.stateCount=0;
	MenuAction.toggleMenuState(true);
}