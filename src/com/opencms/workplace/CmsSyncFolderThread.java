/*
* File   : $Source: /alkacon/cvs/opencms/src/com/opencms/workplace/Attic/CmsSyncFolderThread.java,v $
* Date   : $Date: 2001/07/31 15:50:20 $
* Version: $Revision: 1.7 $
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


package com.opencms.workplace;

import com.opencms.file.*;
import com.opencms.core.*;
import com.opencms.util.*;
import com.opencms.template.*;
import java.util.*;
import java.io.*;

/**
 * Thread for create a new project.
 *
 * @author: Edna Falkenhan
 */

public class CmsSyncFolderThread extends Thread implements I_CmsConstants {

    private Vector m_folders;

    private CmsObject m_cms;

    private boolean m_newProject;

    private I_CmsSession m_session;

    /**
     * Insert the method's description here.
     *
     */

    public CmsSyncFolderThread(CmsObject cms, Vector folders, boolean newProject, I_CmsSession session) {
        m_cms = cms;
        m_folders = folders;
        m_newProject = newProject;
        m_session = session;
    }

    public void run() {
        try {
            // synchronize the resource
            for(int i = 0;i < m_folders.size();i++) {
                // if a new project was created for synchronisation, copy the resource to the project
                if (m_newProject){
                    m_cms.copyResourceToProject((String)m_folders.elementAt(i));
                }
        m_cms.syncFolder((String)m_folders.elementAt(i));
            }
        }
        catch(CmsException e) {
            m_session.putValue(C_SESSION_THREAD_ERROR, Utils.getStackTrace(e));
            if(I_CmsLogChannels.C_PREPROCESSOR_IS_LOGGING && A_OpenCms.isLogging() ) {
                A_OpenCms.log(A_OpenCms.C_OPENCMS_CRITICAL, e.getMessage());
            }
        }
    }
}