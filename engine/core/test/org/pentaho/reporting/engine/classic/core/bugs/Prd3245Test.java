package org.pentaho.reporting.engine.classic.core.bugs;

import java.awt.GraphicsEnvironment;
import java.net.URL;

import junit.framework.TestCase;
import org.pentaho.reporting.engine.classic.core.ClassicEngineBoot;
import org.pentaho.reporting.engine.classic.core.MasterReport;
import org.pentaho.reporting.engine.classic.core.modules.gui.base.PreviewDialog;
import org.pentaho.reporting.engine.classic.core.testsupport.DebugReportRunner;
import org.pentaho.reporting.libraries.resourceloader.Resource;
import org.pentaho.reporting.libraries.resourceloader.ResourceException;
import org.pentaho.reporting.libraries.resourceloader.ResourceManager;

public class Prd3245Test extends TestCase
{
  public Prd3245Test()
  {
  }

  public Prd3245Test(final String name)
  {
    super(name);
  }

  protected void setUp() throws Exception
  {
    ClassicEngineBoot.getInstance().start();
  }

  public void testReport() throws ResourceException
  {
    final URL url = getClass().getResource("Prd-3245.prpt");
    assertNotNull(url);
    final ResourceManager resourceManager = new ResourceManager();
    resourceManager.registerDefaults();
    final Resource directly = resourceManager.createDirectly(url, MasterReport.class);
    final MasterReport report = (MasterReport) directly.getResource();
    DebugReportRunner.execGraphics2D(report);

    if (GraphicsEnvironment.isHeadless())
    {
      return;
    }

    final PreviewDialog previewDialog = new PreviewDialog(report);
    previewDialog.pack();
    previewDialog.setModal(true);
    previewDialog.setVisible(true);
  }

  public void testReportFlow() throws Exception
  {
    final URL url = getClass().getResource("Prd-3245.prpt");
    assertNotNull(url);
    final ResourceManager resourceManager = new ResourceManager();
    resourceManager.registerDefaults();
    final Resource directly = resourceManager.createDirectly(url, MasterReport.class);
    final MasterReport report = (MasterReport) directly.getResource();
    DebugReportRunner.createXmlFlow(report);
  }

  public void testReportStream() throws Exception
  {
    final URL url = getClass().getResource("Prd-3245.prpt");
    assertNotNull(url);
    final ResourceManager resourceManager = new ResourceManager();
    resourceManager.registerDefaults();
    final Resource directly = resourceManager.createDirectly(url, MasterReport.class);
    final MasterReport report = (MasterReport) directly.getResource();
    DebugReportRunner.createXmlStream(report);
  }
}