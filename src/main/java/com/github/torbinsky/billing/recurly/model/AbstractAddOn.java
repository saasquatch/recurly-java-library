/*
 * Copyright 2010-2013 Ning, Inc.
 *
 * Ning licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.github.torbinsky.billing.recurly.model;

import javax.xml.bind.annotation.XmlElement;

public class AbstractAddOn extends RecurlyObject {

    @XmlElement(name = "add_on_code")
    protected String addOnCode;

    public String getAddOnCode() {
        return addOnCode;
    }

    public void setAddOnCode(final Object addOnCode) {
        this.addOnCode = stringOrNull(addOnCode);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AbstractAddOn{");
        sb.append("addOnCode='").append(addOnCode).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final AbstractAddOn that = (AbstractAddOn) o;

        if (addOnCode != null ? !addOnCode.equals(that.addOnCode) : that.addOnCode != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return addOnCode != null ? addOnCode.hashCode() : 0;
    }
}