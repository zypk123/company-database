package cy.its.birt.report.presentation.aggregation.layout;


import org.eclipse.birt.report.presentation.aggregation.control.ToolbarFragment;
import org.eclipse.birt.report.presentation.aggregation.layout.DocumentFragment;
import org.eclipse.birt.report.presentation.aggregation.layout.SidebarFragment;

public class RunFragment extends org.eclipse.birt.report.presentation.aggregation.layout.RunFragment{
	
	@Override
	protected void build( )
	{
		addChild( new SidebarFragment( ) );
		//addChild( new ReportDialogFragment( ) );
		addChild( new ToolbarFragment( ) );
		addChild( new DocumentFragment( ) );
	}
}
