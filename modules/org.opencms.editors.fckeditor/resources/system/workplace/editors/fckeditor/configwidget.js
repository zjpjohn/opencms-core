<%@ page import="
	org.opencms.editors.fckeditor.*,
	org.opencms.jsp.*,
	org.opencms.main.*,
	org.opencms.util.*,
	org.opencms.widgets.*,
	org.opencms.workplace.*,
	org.opencms.workplace.editors.*"
%><%

CmsJspActionElement cms = new CmsJspActionElement(pageContext, request, response);
CmsDialog wp = new CmsDialog(cms);

String site = OpenCms.getSiteManager().getWorkplaceServer();

String configuration = request.getParameter(CmsFCKEditorWidget.PARAM_CONFIGURATION);
CmsHtmlWidgetOption option = new CmsHtmlWidgetOption(configuration);

%>
FCKConfig.AutoDetectLanguage = false;
FCKConfig.DefaultLanguage = "<%= wp.getLocale().getLanguage() %>";

FCKConfig.ProcessHTMLEntities = true;
FCKConfig.ProcessNumericEntities = false;
FCKConfig.IncludeLatinEntities = false;
FCKConfig.IncludeGreekEntities = false;

<%= CmsFCKEditorWidget.getFormatSelectOptionsConfiguration(option) %>

FCKConfig.BaseHref = "<%= site %>";

FCKConfig.ToolbarCanCollapse = true;
FCKConfig.ToolbarStartExpanded	= false;

FCKConfig.SkinPath = FCKConfig.BasePath + "skins/opencms/";

FCKConfig.Plugins.Add("opencms", null, "<%= cms.link("plugins/") %>");
FCKConfig.Plugins.Add("imagegallery", null, "<%= cms.link("/system/workplace/galleries/") %>");
FCKConfig.Plugins.Add("downloadgallery", null, "<%= cms.link("/system/workplace/galleries/") %>");
FCKConfig.Plugins.Add("linkgallery", null, "<%= cms.link("/system/workplace/galleries/") %>");
FCKConfig.Plugins.Add("htmlgallery", null, "<%= cms.link("/system/workplace/galleries/") %>");
FCKConfig.Plugins.Add("tablegallery", null, "<%= cms.link("/system/workplace/galleries/") %>");
//replaced by image gallery: FCKConfig.Plugins.Add("ocmsimage", null, "<%= cms.link("plugins/") %>");
<%

StringBuffer toolbar = new StringBuffer(1024);

toolbar.append("[");

toolbar.append("'Undo','Redo','-','SelectAll','RemoveFormat'");

toolbar.append(",'-','Cut','Copy','Paste','PasteText','PasteWord'");

CmsFCKEditorWidget.buildFontButtons(toolbar, option);

toolbar.append("]");

CmsFCKEditorWidget.buildFormatButtons(toolbar, option);

// append customized OpenCms buttons
if (CmsFCKEditorWidget.buildMiscButtons(toolbar, option)) {
	toolbar.append(",'-',");
} else {
	toolbar.append(",[");
}

toolbar.append("'SpecialChar'");

if (!option.isButtonHidden("print")) {
	toolbar.append(",'-','Print'");
}

toolbar.append(",'-','FitWindow']");

%>
FCKConfig.ToolbarSets["OpenCmsWidget"] = [
        <%= toolbar %>
];

FCKConfig.Keystrokes = [
	[ CTRL + 65 /*A*/, true ],
	[ CTRL + 67 /*C*/, true ],
	[ CTRL + 70 /*F*/, true ],
	[ CTRL + 83 /*S*/, true ],
	[ CTRL + 88 /*X*/, true ],
	[ CTRL + 86 /*V*/, 'Paste' ],
	[ SHIFT + 45 /*INS*/, 'Paste' ],
	[ CTRL + 90 /*Z*/, 'Undo' ],
	[ CTRL + 89 /*Y*/, 'Redo' ],
	[ CTRL + SHIFT + 90 /*Z*/, 'Redo' ],
	[ CTRL + 76 /*L*/, 'Link' ],
	[ CTRL + 66 /*B*/, 'Bold' ],
	[ CTRL + 73 /*I*/, 'Italic' ],
	[ CTRL + 85 /*U*/, 'Underline' ],
	[ CTRL + SHIFT + 83 /*S*/, true ],
	[ CTRL + ALT + 13 /*ENTER*/, 'FitWindow' ]<% 
	if (option.showSourceEditor()) { %>,
	[ CTRL + 9 /*TAB*/, 'Source' ]<%
	} %>
] ;

FCKConfig.PreserveSessionOnFileBrowser = true;

FCKConfig.ImageUpload = false;
FCKConfig.ImageBrowserURL = FCKConfig.BasePath + "filemanager/browser/default/browser.html?Type=Image&Connector=<%= cms.link(CmsEditor.PATH_EDITORS + "fckeditor/filebrowser/connector.jsp") %>";
FCKConfig.ImageUploadURL = "<%= cms.link(CmsEditor.PATH_EDITORS + "fckeditor/filebrowser/connector.jsp?Type=Image") %>";
FCKConfig.ImageBrowserWindowWidth  = 700;
FCKConfig.ImageBrowserWindowHeight = 500;

FCKConfig.LinkUpload = false;
FCKConfig.LinkBrowserURL = FCKConfig.BasePath + "filemanager/browser/default/browser.html?Connector=<%= cms.link(CmsEditor.PATH_EDITORS + "fckeditor/filebrowser/connector.jsp") %>";
FCKConfig.LinkUploadURL = "<%= cms.link(CmsEditor.PATH_EDITORS + "fckeditor/filebrowser/connector.jsp") %>";
FCKConfig.LinkBrowserWindowWidth  = 700;
FCKConfig.LinkBrowserWindowHeight = 500;