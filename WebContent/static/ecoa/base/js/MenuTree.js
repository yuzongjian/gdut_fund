/**
 * @class JavaScript  树形菜单控件类。<br />
 * 此类功能包括：<br />
 * 树形菜单控件实现。
 * @constructor 构造函数
 * @throws MemoryException 如果没有足够的内存
 * @return 返回菜单对象
 */
function MenuTree(){
	/**
	 * @private 私有函数
	 * 初始化设置params参数集参数值
	 * @param params Array 参数集 参数必须 是
	 * @return 无
	 * @type void
	 */
	var paramsDefault = function(params){
		 function param_default(pname, def) { 
		 	if (typeof params[pname] == "undefined"){
		 		params[pname] = def; 
		 	}
		 };
		 param_default("id",null);
		 param_default("treeData",new Array());
		 param_default("topMenuId","");
		 param_default("animated","normal");
		 param_default("collapsed",true);
		 //param_default("unique",true);
	};
	
	var topMenuId = "";
	
	var params;
	
	var treeData = new Array();
	
	/**
	 * 生成树形菜单相应的HTML代码，并加载相应的CSS和JavaScript实现功能。<br />
	 * 相关参数集的说明如下：<br />
	 * id : "" String 树形菜单ID <br />
	 * treeData : Object[] Array 树形菜单数据，存放MenuTreeBean对象的数组 <br />
	 * animated : "normal" 树节点显示展开和收起的速度，有三个值["slow","normal","fast"]可以设置，默认值为 "normal"<br />
	 * collapsed : true boolean 树的所有节点第一次显示时是否是只显示根节点，true只显示根节点，false不限制，默认值是true 参数必须 否<br />
	 * unique : true boolean 是否只允许一个树节点展开 true只允许一个树节点展开，false不限制，默认值是true 参数必须 否<br />
	 * @param params Array 参数集 参数必须 是
	 * @return 无
	 */
	this.createTree = function(params){
	    this.params = params;
		paramsDefault(params);
		if(params.id != null && params.treeData.length > 0) {
			if(params.treeData == '' || params.treeData == 'undefined'){
				return false;
			}
			if(params.treeData.length < 1){
				return false;
			}
			topMenuId = params.topMenuId;
			try {
				//分析树形菜单数据
				analysisMenuTreeData(params.treeData);
				
				//遍历输出树形结构
				traversalMenuTree(params.topMenuId);
			}catch(ex){
				return false;
			}
		}
	};
	
	/**
	 * 遍历树形菜单数据并组输出树形结构。
	 * 为递归方法输出树形结构
	 * @param treeData Array {@link MenuTreeBean} 数据树形菜单数据集 参数必须 是
	 * @param parentId String 树形菜单根节点标记 '0' 参数必须 是
	 * @param id String 树形菜单id 参数必须 是
	 * @return 无
	 * @type void
	 */
	var traversalMenuTree = function(parentId) {
		var nodeArray = queryMenuTreeData(parentId);
		//对数据组进行排序
		nodeArray.sort(function(obj1,obj2){
				if(obj1.getMenuOrderId() < obj2.getMenuOrderId()){
					return -1;
				}else if(obj1.getMenuOrderId() == obj2.getMenuOrderId()){
					return 0;
				}else if(obj1.getMenuOrderId() > obj2.getMenuOrderId()){
					return 1;
				}else{
					return obj1.getMenuOrderId() < obj2.getMenuOrderId();
				}
			});
		if(nodeArray.length != 0) {
			for(var i = 0; i < nodeArray.length; i++) {
				if(topMenuId == nodeArray[i].getMenuPId()) {
					createTreeNode(nodeArray[i], parentId);
				}else {
					createSubTreeNode(nodeArray[i]);
				}
			
				if(hasChildNode(nodeArray[i].getMenuId())) {
		    		traversalMenuTree(nodeArray[i].getMenuId());
				}
			}
		}
	};
	
	/** 画出一级节点 */
	var createTreeNode = function(node) {
		var divObj = document.getElementById("div_" + node.getMenuPId());

		if(!divObj && topMenuId == node.getMenuPId()) {
		    divObj = document.getElementById("root");
		}

		var nodeHtml = "";
		if(node.getMenuLeafFlag() == '0') {
			nodeHtml += "<div class='FixFirstMenu' onClick='FixShowSubMenu(\"" + node.getMenuId() + "\");' id=\"f_" + node.getMenuId() + "\"><a href='#' onFocus='this.blur()' id='a_" + node.getMenuId() + "'><b class='i_grswzx'></b>" + node.getMenuName() + "<span class='FixSpanMenu'></span></a></div>";
			nodeHtml += "<div class='FixSecondMenu' id='div_" + node.getMenuId() + "' filled='0' style='display:none'><div>";
		} else {
			if(node.getMenuTarget()=="mainFrame"){
				nodeHtml += "<div class='FixFirstMenu' onclick='openFrame(\"" + TargetUrl + "\")'><a onFocus='this.blur()' href='#' id='a_" + node.getMenuId() + "'><b class='i_grswzx'></b>" + node.getMenuName() + "<span></span></a></div>";
			}else{
				nodeHtml += "<div class='FixFirstMenu'><a onFocus='this.blur()' href='" + TargetUrl + "' target='" + node.getMenuTarget() + "' id='a_" + node.getMenuId() + "'>" + node.getMenuName() + "<b></b></a></div>";
			}
		}

		

		divObj.innerHTML += nodeHtml;
		divObj.filled = "1";
		divObj.style.display = "";
	};
	
	/**画下级子节点*/
	var createSubTreeNode = function(node){
		var divObj = document.getElementById("div_" + node.getMenuPId());

		var nodeHtml = "<ul>";
		if(node.getMenuLeafFlag() == '0') {
			nodeHtml += "<li><a onFocus='this.blur()' onclick='FixShowSubMenu(\"" + node.getMenuId() + "\");return false;' href='#' id='a_" + node.getMenuId() + "' class='menu'>" + node.getMenuName() + "</a></li>";
			nodeHtml += "<div class='thirdMenu' id='div_" + node.getMenuId() + "' filled='0' style='display:none'></div>";
		}else {
			if(node.getMenuTarget()=="mainFrame"){
				nodeHtml += "<li><a onFocus='this.blur()' onclick='MenuTree.SaveMemuCookie(\"a_" + node.getMenuId() + "\");return true;' href='" + node.getMenuDo() + "' target='" + node.getMenuTarget() + "' id='a_" + node.getMenuId() + "'>" + "&nbsp;" +node.getMenuName() + "</a></li>";
			}else{
				nodeHtml += "<li><a onFocus='this.blur()'  href='" + node.getMenuDo() + "' target='" + node.getMenuTarget() + "' id='a_" + node.getMenuId() + "'>" + node.getMenuName() + "</a></li>";
			}
		}

		divObj.innerHTML += nodeHtml + "</ul>";
		divObj.filled = "1";
	};
	
	
	this.SaveMemuCookie = function(MenuId) {
		// 去掉其他的Curr
		$(".Curr").removeClass("Curr");
		// 自身加上Curr
		$("#"+MenuId).addClass("Curr");
		
	/*
		保存菜单cookie
	*/
		top.document.cookie = "LeftNode=" + MenuId;
		top.document.cookie = "MainFrameUrl=";
	};
	
	this.showSubMenu = function(nodeId) {
		
		
//        var FixSideBar=document.getElementById('root');
//        FixSideBar.style.height=document.documentElement.clientHeight+'px';
//		alert('fixsidebar')
		
	    var aObj = document.getElementById("a_" + nodeId);

		var divObj = document.getElementById("div_" + nodeId);
		if (divObj.style.display == "none") {
			divObj.style.display = "";
			aObj.className = "on";
		} else {
			divObj.style.display = "none";
			if(divObj.className == "secondMenu") {
				aObj.className = "";
			}else{
				aObj.className = "menu";
			}
		}
	};

	/**
	 * 分析处理树形菜单数据，形成树形数据结构的数据储存
	 * @param treeData 菜单数据
	 * @return 返回分析后的树形菜单数据
	 * @type Array <{@link MenuTreeBean}>
	 */
	var analysisMenuTreeData = function(treeData) {
		if(treeData.length < 1) {
			return treeData;
		}
		var i = 0;
		for(i = 0; i < treeData.length; i++) {
			var treeBean = treeData[i];
			var treeid = treeBean.getMenuId();
			if(hasChildNode(treeBean.getMenuId())) {
				treeBean.setHasChildNode(true);
			}else{
				treeBean.setHasChildNode(false);
			}
		}
	};
	/**
	 * 根据条件查找数据
	 * 返回一个数组
	 * @param id String 查找条件菜单父节点
	 * @return 返回查询结果集
	 * @type Array <{@link MenuTreeBean}>
	 */
	var queryMenuTreeData = function(parentId){
		if(treeData.length < 1 || parentId == '' || parentId == null || parentId == 'undefined'){
			return null;
		}
		var i = 0;
		var rs = new Array();
		var flag = 0;
		for(i = 0; i < treeData.length; i++){
			var treeBean = treeData[i];
			if(treeBean.getMenuPId() == parentId){
				rs[flag] = treeBean;
				flag = flag + 1;
			}
		}
		if(flag == 0){
			return null;
		}
		return rs;
	};
	/**
	 * 判断节点是否有子节点的
	 * @param id String 查找条件菜单父节点
	 * @return 返回判断结果，没有子节点返回 false，有子节点返回 true
	 * @type boolean
	 */
	var hasChildNode = function(id){
		for(var i = 0; i < treeData.length; i++){
			var treeBean = treeData[i];
			if(treeBean.getMenuPId() == id){
				return true;
			}
		}
		return false;
	};
	/**
	 * 创建树形菜单的数据集
	 * 返回Array {@link MenuTreeBean}
	 * 如果初始化数据失败返回空数据的Array对象
	 * @param params 树形菜单
	 * @return 返回一个数组对象，数组存放MenuTreeBean对象。
	 * @type Array
	 */
	this.initMenuTreeData = function(params){
		//var treeData = new Array();
		
		if(params == '' || params == 'undefined'){
			return treeData;
		}
		if(params.length < 1){
			return treeData;
		}
		try {
			var i = 0;
			for(i = 0; i < params.length; i ++){
				var mtb = new MenuTreeBean();
				mtb.init(params[i].id,params[i].pid,params[i].code,params[i].pname,params[i].leafFlag,params[i].desc,params[i].pdo,params[i].target,params[i].orderid);
				treeData[treeData.length] = mtb;
			}
			//对数据组进行排序
			treeData.sort(function(obj1,obj2){
					if(obj1.getMenuId() < obj2.getMenuId()){
						return -1;
					}else if(obj1.getMenuId() == obj2.getMenuId()){
						return 0;
					}else if(obj1.getMenuId() > obj2.getMenuId()){
						return 1;
					}else{
						return obj1.getMenuId() < obj2.getMenuId();
					}
				});
		}catch(ex) {
			//alert("ex:树形菜单数组排序出错!");
		}
		
		return treeData;
	};
}
/**
 * @class JavaScript  树形菜单Bean类。<br />
 * 定义菜单的属性
 * @constructor 构造函数
 * @throws MemoryException 如果没有足够的内存
 * @return 菜单对象
 */
function MenuTreeBean(){
	/**
	 * 菜单id
	 * @type String
	 */
	var menuId;
	/**
	 * 获取菜单ID
	 * @return 返回菜单ID
	 * @type String
	 */
	this.getMenuId = function(){
		return menuId;
	};
	/**
	 * 菜单父节点id <br />
	 * 0 表示没有父节点，即为根节点，默认值为：0
	 * @type String
	 */
	var menuPId = "0";
	/**
	 * 获取菜单父节点ID
	 * @return 返回菜单父节点id
	 * @type String
	 */
	this.getMenuPId = function(){
		return menuPId;
	};
	/**
	 * 菜单编码
	 * @type String
	 */
	var menuCode = "";
	/**
	 * 获取菜单编码
	 * @return 返回菜单编码
	 * @type String
	 */
	this.getMenuCode = function(){
		return menuCode;
	};
	/**
	 * 菜单名称 即树形菜单显示的名称
	 * @type String 
	 */
	var menuName = "";
	/**
	 * 获取菜单名称
	 * @return 返回菜单名称
	 * @type String
	 */
	this.getMenuName = function(){
		return menuName;
	};
	
	var menuLeafFlag = "0";
	/**
	 * 获取菜单是否叶子节点标识
	 * @return 返回菜单叶子节点标识
	 * @type String
	 */
	this.getMenuLeafFlag = function() {
	    return menuLeafFlag;
	};
	/**
	 * 菜单描述
	 * @type String
	 */
	var menuDesc = "";
	/**
	 * 获取菜单描述
	 * @return 返回菜单描述
	 * @type String
	 */
	this.getMenuDesc = function(){
		return menuDesc;
	};
	/**
	 * 菜单链接 
	 * 如果菜单menuPId 为：0，则菜单链接不需要设置。
	 * @type String
	 */
	var menuDo = "";
	/**
	 * 获取菜单链接
	 * @return 返回菜单链接
	 * @type String
	 */
	this.getMenuDo = function(){
		if(menuDo.toLowerCase().indexOf("javascript")==-1){
			return encodeURI(menuDo);
		}else{
			return menuDo;
		}
	};
	/**
	 * 菜单链接目标
	 * @type String
	 */
	var menuTarget = "";
	/**
	 * 获取菜单链接目标
	 * @return 返回菜单链接目标
	 * @type String
	 */
	this.getMenuTarget = function(){
		return menuTarget;
	};
	/**
	 * 菜单排序ID
	 * @type String
	 */
	var menuOrderId = "";
	/**
	 * 获取菜单排序ID
	 * @return 返回菜单排序ID
	 * @type String
	 */
	this.getMenuOrderId = function(){
		return menuOrderId;
	};
	
	/**
	 * 初始化设置菜单Bean对象中的属性值
	 * @return 无
	 * @type void
	 */
	this.init = function(id,pid,code,pname,leafFlag,desc,pdo,target,orderid){
		menuId = id;
		menuPId = pid;
		menuCode = code;
		menuName = pname;
		menuLeafFlag = leafFlag;
		menuDesc = desc;
		menuDo = pdo;
		menuTarget = target;
		menuOrderId = orderid;
	};
	/**
	 * 是否有子节点，默认值为false
	 * @type boolean
	 */
	var hasChildNode = false;
	/**
	 * 获取菜单链接目标
	 * @return 返回菜单链接目标
	 * @type boolean
	 */
	this.hasChildNode = function(){
		return hasChildNode;
	};
	/**
	 * 设置是否有子节点
	 * @param haschild boolean true：表示有子节点，false：表示没有了节点
	 * @return 无
	 * @type void
	 */
	this.setHasChildNode = function(haschild){
		hasChildNode = haschild;
	};
}

/**
 * @ignore 
 * 树形菜单组件类实例对象,即引入MenuTree.js文件，
 * 可以通过MenuTree.XXX()的方式使用树形菜单组件。
 */
var MenuTree = new MenuTree();


