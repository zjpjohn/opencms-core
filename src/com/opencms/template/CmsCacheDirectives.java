/*
* File   : $Source: /alkacon/cvs/opencms/src/com/opencms/template/Attic/CmsCacheDirectives.java,v $
* Date   : $Date: 2001/07/31 15:50:16 $
* Version: $Revision: 1.16 $
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

package com.opencms.template;

import com.opencms.core.*;
import com.opencms.template.cache.CmsTimeout;
import com.opencms.file.*;
import java.util.*;

/**
 * Collection of all information about cacheability and
 * used keys.
 *
 * @author Alexander Lucas
 * @author Hanjo Riege
 * @version $Revision: 1.16 $ $Date: 2001/07/31 15:50:16 $
 */
public class CmsCacheDirectives extends A_CmsCacheDirectives implements I_CmsLogChannels {


    /**
     * Constructor for initializing all caching properties with the same boolean
     * value.
     * @param b Boolean value that should be set for all caching properties
     */
    public CmsCacheDirectives(boolean b) {
        if(b) {
            m_cd = C_CACHE_INTERNAL | C_CACHE_PROXY_PRIVATE | C_CACHE_PROXY_PUBLIC | C_CACHE_EXPORT | C_CACHE_STREAM;
        } else {
            m_cd = 0;
        }
        m_userSetExport = true;
        m_userSetProxyPrivate = true;
        m_userSetProxyPublic = true;
    }

    /**
     * Constructor for initializing all caching properties given boolean
     * values.
     * @param internal Initial value for "internal cacheable" property.
     * @param proxyPriv Initial value for "proxy private cacheable" property.
     * @param proxyPub Initial value for "internal cacheable" property.
     * @param export Initial value for "exportable" property.
     * @param stream Initial value for "streamable" property.
     */
    public CmsCacheDirectives(boolean internal, boolean proxyPriv, boolean proxyPub, boolean export, boolean stream) {
        m_cd = 0;
        m_cd |= internal?C_CACHE_INTERNAL:0;
        m_cd |= proxyPriv?C_CACHE_PROXY_PRIVATE:0;
        m_cd |= proxyPub?C_CACHE_PROXY_PUBLIC:0;
        m_cd |= export?C_CACHE_EXPORT:0;
        m_cd |= stream?C_CACHE_STREAM:0;

        m_userSetExport = true;
        m_userSetProxyPrivate = true;
        m_userSetProxyPublic = true;
    }

    /**
     * Constructor
     * @param internal Initial value for "internal cacheable" property.
     * @param stream Initial value for "streamable" property.
     */
    public CmsCacheDirectives(boolean internal, boolean stream) {
        m_cd = 0;
        m_cd |= internal?C_CACHE_INTERNAL:0;
        m_cd |= stream?C_CACHE_STREAM:0;
    }

    /**
     * enables or disables the proxy public cache for this element.
     * @param proxyPublic true if the flag should be set in the response header.
     */
    public void setProxyPublicCacheable(boolean proxPublic){
        m_userSetProxyPublic = true;
        setExternalCaching(isInternalCacheable(), isProxyPrivateCacheable(),
                            proxPublic, isExportable(), isStreamable());
    }

    /**
     * enables or disables the proxy private cache for this element.
     * @param proxyPrivate true if the flag should be set in the response header.
     */
    public void setProxyPrivateCacheable(boolean proxPrivate){
        m_userSetProxyPrivate = true;
        setExternalCaching(isInternalCacheable(), proxPrivate,
                            isProxyPublicCacheable(), isExportable(), isStreamable());
    }

    /**
     * enables or disables the export for this element.
     * @param export true if the flag should be set in the response header.
     */
    public void setExport(boolean export){
        m_userSetExport = true;
        setExternalCaching(isInternalCacheable(), isProxyPrivateCacheable(),
                            isProxyPublicCacheable(), export, isStreamable());
    }

    /**
     * set the timeout object(used if the element should be reloaded every x minutes.
     * if the timeout object says so the proxyCache is disabled.
     * @param timeout a CmsTimeout object.
     */
    public void setTimeout(CmsTimeout timeout) {
        m_timecheck = true;
        m_timeout = timeout;
        if ( !m_timeout.isProxyCacheable()){
            setProxyPrivateCacheable(false);
            setProxyPublicCacheable(false);
        }
    }
}
