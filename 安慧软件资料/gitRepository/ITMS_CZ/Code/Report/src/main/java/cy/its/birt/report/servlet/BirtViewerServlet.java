package cy.its.birt.report.servlet;


import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.birt.report.presentation.aggregation.layout.FramesetFragment;
import org.eclipse.birt.report.service.BirtReportServiceFactory;
import org.eclipse.birt.report.service.BirtViewerReportService;
import org.eclipse.birt.report.servlet.ViewerServlet;

import cy.its.birt.report.presentation.aggregation.layout.RunFragment;

@SuppressWarnings("serial")
public class BirtViewerServlet extends ViewerServlet {
	
	/**
	 * Local initialization.
	 * 
	 * @return
	 */
	@Override
	protected void __init( ServletConfig config )
	{
		BirtReportServiceFactory.init( new BirtViewerReportService( config
				.getServletContext( ) ) );

		// handle 'frameset' pattern
		viewer = new FramesetFragment( );
		viewer.buildComposite( );
		viewer.setJSPRootPath( "/webcontent/birt" ); //$NON-NLS-1$

		// handle 'run' pattern
		run = new RunFragment( );
		run.buildComposite( );
		run.setJSPRootPath( "/webcontent/birt" ); //$NON-NLS-1$		
	}
	/**
	 * Local authentication. Alwasy returns true.
	 * 
	 * @param request
	 *            incoming http request
	 * @param response
	 *            http response
	 * @return
	 */
	@Override
	protected boolean __authenticate( HttpServletRequest request,
			HttpServletResponse response )
	{
		System.out.println("使用自定义认证方法了哦。");
		return true;
	}
}