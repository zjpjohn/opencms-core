/*
* File   : $Source: /alkacon/cvs/opencms/src/com/opencms/file/Attic/CmsFolder.java,v $
* Date   : $Date: 2001/07/31 15:50:13 $
* Version: $Revision: 1.13 $
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

package com.opencms.file;

import com.opencms.core.*;
import java.io.*;
/**
 * This class describes a folder in the Cms.
 *
 * @author Michael Emmerich
 * @version $Revision: 1.13 $ $Date: 2001/07/31 15:50:13 $
 */
public class CmsFolder extends CmsResource implements I_CmsConstants,
                                                      Cloneable,
                                                      Serializable {

     /**
      * Constructor, creates a new CmsFolder object.
      *
      * @param resourceId The database Id.
      * @param parentId The database Id of the parent folder.
      * @param fileId The id of the content.
      * @param resourceName The name (including complete path) of the resouce.
      * @param resourceType The type of this resource.
      * @param rescourceFlags The flags of thei resource.
      * @param userId The id of the user of this resource.
      * @param groupId The id of the group of this resource.
      * @param projectId The project id this resource belongs to.
      * @param accessFlags The access flags of this resource.
      * @param state The state of this resource.
      * @param lockedBy The user id of the user who has locked this resource.
      * @param dateCreated The creation date of this resource.
      * @param dateLastModified The date of the last modification of the resource.
      * @param resourceLastModifiedBy The user who changed the file.
      */
     public CmsFolder(int resourceId, int parentId,int fileId,
                        String resourceName, int resourceType, int resourceFlags,
                        int user, int group, int projectId,
                        int accessFlags, int state, int lockedBy,
                        long dateCreated, long dateLastModified
                        ,int resourceLastModifiedBy, int lockedInProject){

        // create the CmsResource.
        super(resourceId, parentId,fileId,resourceName,
              resourceType,resourceFlags,
              user,group,projectId,
              accessFlags,state,lockedBy,
              C_UNKNOWN_LAUNCHER_ID,C_UNKNOWN_LAUNCHER,
              dateCreated,dateLastModified,
              resourceLastModifiedBy,-1, lockedInProject);
   }
    /**
    * Clones the CmsFolder by creating a new CmsFolder.
    * @return Cloned CmsFolder.
    */
    public Object clone() {
        return new CmsFolder(this.getResourceId(), this.getParentId(), this.getFileId(),
                             new String(this.getAbsolutePath()),this.getType(),
                             this.getFlags(), this.getOwnerId(), this.getGroupId(),
                             this.getProjectId(),this.getAccessFlags(),
                             this.getState(),this.isLockedBy(),this.getDateCreated(),
                             this.getDateLastModified(), this.getResourceLastModifiedBy(),
                             this.getLockedInProject());
    }
}
