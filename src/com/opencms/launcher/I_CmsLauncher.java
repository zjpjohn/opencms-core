/*
* File   : $Source: /alkacon/cvs/opencms/src/com/opencms/launcher/Attic/I_CmsLauncher.java,v $
* Date   : $Date: 2001/07/31 15:50:16 $
* Version: $Revision: 1.12 $
*
* This library is part of OpenCms -
* the Open Source Content Mananagement System
*
* Copyright (C) 2001  The OpenCms Group
*
* This library is free software; you can redistribute it and/or
* modify it under the terms of the GNU Lesser General Public
* License as published by the Free Software Foundation; either
* version 2.1 of the License, or (at your option) any later version.
*
* This library is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
* Lesser General Public License for more details.
*
* For further information about OpenCms, please see the
* OpenCms Website: http://www.opencms.org 
*
* You should have received a copy of the GNU Lesser General Public
* License along with this library; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package com.opencms.launcher;

import com.opencms.file.*;
import com.opencms.core.*;
import javax.servlet.http.*;

/**
 * Common interface for OpenCms launchers.
 * Classes for each customized launcher have to be implemtented.
 * 
 * @author Alexander Lucas
 * @version $Revision: 1.12 $ $Date: 2001/07/31 15:50:16 $
 */
public interface I_CmsLauncher {
    
    /** Constants used as launcher IDs */
    public final static int C_TYPE_DUMP = 1, C_TYPE_JAVASCRIPT = 2, C_TYPE_XML = 3, C_TYPE_LINK = 4, C_TYPE_PDF = 5;
    public void clearCache();
    
    /**
     * Gets the ID that indicates the type of the launcher.
     * @return launcher ID
     */
    public int getLauncherId();
    
    /**
     * Start launch method called by the OpenCms system to show a resource   
     *  
     * @param cms CmsObject Object for accessing system resources.
     * @param file CmsFile Object with the selected resource to be shown.
     * @param startTemplateClass Name of the template class to start with.
     * @param openCms a instance of A_OpenCms for redirect-needs
     * @exception CmsException
     */
    public void initlaunch(CmsObject cms, CmsFile file, String startTemplateClass, A_OpenCms openCms) throws CmsException;
}
