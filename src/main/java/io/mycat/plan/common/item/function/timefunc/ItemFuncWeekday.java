package io.mycat.plan.common.item.function.timefunc;

import java.math.BigInteger;
import java.util.List;

import io.mycat.plan.common.item.Item;
import io.mycat.plan.common.item.function.ItemFunc;
import io.mycat.plan.common.item.function.primary.ItemIntFunc;
import io.mycat.plan.common.time.MySQLTime;
import io.mycat.plan.common.time.MyTime;

public class ItemFuncWeekday extends ItemIntFunc {

	public ItemFuncWeekday(List<Item> args) {
		super(args);
	}

	@Override
	public final String funcName() {
		return "weekday";
	}

	@Override
	public BigInteger valInt() {
		MySQLTime ltime = new MySQLTime();

		if (getArg0Date(ltime, MyTime.TIME_NO_ZERO_DATE))
			return BigInteger.ZERO;

		return BigInteger.valueOf(MyTime.calc_weekday(MyTime.calc_daynr(ltime.year, ltime.month, ltime.day), false));
	}

	@Override
	public void fixLengthAndDec() {
		fixCharLength(1);
		maybeNull = true;
	}
	
	@Override
	public ItemFunc nativeConstruct(List<Item> realArgs) {
		return new ItemFuncWeekday(realArgs);
	}

}