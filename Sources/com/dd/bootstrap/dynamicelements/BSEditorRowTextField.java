package com.dd.bootstrap.dynamicelements;

import com.dd.bootstrap.components.BSDynamicElement;
import com.webobjects.appserver.WOAssociation;
import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOElement;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSDictionary;

import er.extensions.components._private.ERXWOTextField;

/**
 * BSEditorRowTextField
 * 
 * Additional bindings:
 * 
 * @binding label
 * @binding required
 * @binding helpText
 * @binding unit
 * 
 * @author robin
 *
 */
public class BSEditorRowTextField extends ERXWOTextField {
	
	private WOAssociation _label;		// String
	private WOAssociation _required;	// boolean
	private WOAssociation _helpText;	// String
	private WOAssociation _unit;		// String
	
	@SuppressWarnings("rawtypes")
	public BSEditorRowTextField(String tagname, NSDictionary nsdictionary, WOElement woelement) {
		super(tagname, nsdictionary, woelement);
		
		BSDynamicElementsHelper.AppendCSS(_associations, this);
		
		_label = _associations.removeObjectForKey("label");
		_required = _associations.removeObjectForKey("required");
		_helpText = _associations.removeObjectForKey("helpText");
		_unit = _associations.removeObjectForKey("unit");
	}
	
	@Override
	public void appendToResponse(WOResponse response, WOContext context) {
		BSDynamicElement.InjectCSSAndJS(response, context);
		boolean hasUnit = (_unit != null && _unit.valueInComponent(context.component()) != null);
		
		response.appendContentString("<div class=\"form-group\"><label class=\"col-sm-2 control-label\">");
		if (_label != null && _label.valueInComponent(context.component()) != null) {
			response.appendContentHTMLString((String) _label.valueInComponent(context.component()));
			if (_required != null && (Boolean) _required.valueInComponent(context.component())) {
				response.appendContentString("*");
			}
		}
		
		response.appendContentString("</label><div class=\"col-sm-10\">");
		
		if (hasUnit) {
			response.appendContentString("<div class=\"input-group\">");
		}
		
		super.appendToResponse(response, context);
		
		if (hasUnit) {
			response.appendContentString("<span class=\"input-group-addon\">");
			response.appendContentHTMLString((String) _unit.valueInComponent(context.component()));
			response.appendContentString("</span></div>");
		}
		
		if (_helpText != null && _helpText.valueInComponent(context.component()) != null) {
			response.appendContentString("<span class=\"help-block\">");
			response.appendContentString((String) _helpText.valueInComponent(context.component()));
			response.appendContentString("</span>");
		}
		response.appendContentString("</div></div>");
	}

	
	
}
