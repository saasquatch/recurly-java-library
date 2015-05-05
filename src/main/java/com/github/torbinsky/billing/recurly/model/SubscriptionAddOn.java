/*
 * Copyright 2013 Torbinsky
 *
 * Torbinsky licenses this file to you under the Apache License, version 2.0
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
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "subscription_add_on")
public class SubscriptionAddOn extends RecurlyObject {

    @XmlElement(name = "add_on_code")
    private String addOnCode;
    
    @XmlElement(name = "unit_amount_in_cents")
	private Integer unitAmountInCents;

    @XmlElement(name = "quantity")
	private Integer quantity;

	public String getAddOnCode() {
		return addOnCode;
	}

	public void setAddOnCode(String addOnCode) {
		this.addOnCode = addOnCode;
	}

	public Integer getUnitAmountInCents() {
		return unitAmountInCents;
	}

	public void setUnitAmountInCents(Integer unitAmountInCents) {
		this.unitAmountInCents = unitAmountInCents;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("SubscriptionAddOn");
        sb.append("{addOnCode='").append(addOnCode).append('\'');
        sb.append(", quantity='").append(quantity).append('\'');
        sb.append(", unitAmountInCents=").append(unitAmountInCents);
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

		final SubscriptionAddOn addOn = (SubscriptionAddOn) o;

		if (addOnCode != null ? !addOnCode.equals(addOn.addOnCode) : addOn.addOnCode != null) {
            return false;
        }
		
        if (quantity != null ? !quantity.equals(addOn.quantity) : addOn.quantity != null) {
            return false;
        }
        
        if (unitAmountInCents != null ? !unitAmountInCents.equals(addOn.unitAmountInCents) : addOn.unitAmountInCents != null) {
            return false;
        }

		return true;
	}

	@Override
	public int hashCode() {
		int result = (addOnCode != null ? addOnCode.hashCode() : 0);
		result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
		result = 31 * result + (unitAmountInCents != null ? unitAmountInCents.hashCode() : 0);
		return result;
	}
	
}
