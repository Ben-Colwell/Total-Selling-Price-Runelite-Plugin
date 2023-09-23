/* BSD 2-Clause License
 * Copyright (c) 2023, Erishion Games LLC <https://github.com/Erishion-Games-LLC>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.erishiongamesllc.totalsellingprice;

public enum ShopInfo
{
	BANDIT_DUTY_FREE_GENERAL_STORE("Bandit Duty Free", 0.9f, 0.6f, 0.03f),
	MARTIN_THWAITS_LOST_AND_FOUND("Martin Thwait's Lost and Found.", 1.0f, 0.6f, 0.02f),
	WEST_ARDOUGNE_GENERAL_STORE("West Ardougne General Store", 1.2f, 0.55f, 0.02f),
	POLLNIVNEACH_GENERAL_STORE("Pollnivneach general store.", 1.0f, 0.55f, 0.01f),
	LEGENDS_GUILD_GENERAL_STORE("Legends Guild General Store.", 1.55f, 0.55f, 0.01f),
	;


	private final String name;
	private final float sellPercent;
	private final float buyPercent;
	private final float changePercent;

	ShopInfo(String name, float sellPercent, float buyPercent, float changePercent)
	{
		this.name = name;
		this.sellPercent = sellPercent;
		this.buyPercent = buyPercent;
		this.changePercent = changePercent;
	}

	public String getName()
	{
		return name;
	}

	public float getSellPercent()
	{
		return sellPercent;
	}

	public float getBuyPercent()
	{
		return buyPercent;
	}

	public float getChangePercent()
	{
		return changePercent;
	}
}