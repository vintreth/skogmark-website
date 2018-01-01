package ru.skogmark.go.gen.core.domain.old;

/**
 * PROPRIETARY/CONFIDENTIAL.  Use of this product is subject to license terms.
 * Copyright (c) 2016 NVision Group, Inc. All rights reserved.
 * <p>
 * 01.02.2017 15:46
 *
 * @author Kirill Bogdanov (ksbogdan@nvision-group.com)
 */
public interface RoleAwareEntity {
    Role getRole();

    void setRole(Role role);
}
