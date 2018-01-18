/*
 * Copyright 2010-2012 Ning, Inc.
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

package com.github.torbinsky.billing.recurly.model.list;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.github.torbinsky.billing.recurly.model.Plan;

@XmlRootElement(name = "plans")
public class Plans extends RecurlyObjects<Plan> {

    @XmlTransient
    public static final String PLANS_RESOURCE = "/plans";
    
    @XmlElement(name = "plan")
    private List<Plan> plans = new ArrayList<>();

	@Override
	public List<Plan> getObjects() {
		return plans;
	}
	
	public void setPlans(List<Plan> planList) {
        this.plans = planList;
    }
}
