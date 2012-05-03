%{
    models = [];
    System.out.println("BASEBUDS: Type Route Loading");
    for(controllerClass in play.Play.classloader.getAssignableClasses(_('controllers.basebuds.BaseBuds'))) {
        resourceModel = _('controllers.basebuds.BaseBuds$ObjectType').get(controllerClass) 
        if(resourceModel != null) {
            models.add(resourceModel)
        }
  
        
    }
    
}%

%{ models.eachWithIndex() { item, i -> }%
	%{
		attrs = [:]
		attrs.put('type', item)
                System.out.println(attrs); 
	}%
    #{doBody vars:attrs /}
%{ } }%