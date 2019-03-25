/***********************************************************************
 *                           常用的日期常量                               *
 *                     注：调用方式，Constant.常量名                        *
 * ********************************************************************/
var Constant ={
		/*
		 * 常量含义：【当前年份】
		 */
		CURR_YEAR:new Date().getFullYear(),
		/*
		 * 常量含义：【去年年份】
		 */
		NEXT_YEAR:new Date().getFullYear()-1,
		/*
		 * 常量含义：【当前月份】
		 */
		CURR_MONTH:dateUtil.month(),
		/*
		 * 常量含义：【上月份】
		 */
		PRE_MONTH:dateUtil.preMonth(),
		/*
		 * 常量含义：【当前日期】
		 */
		CURR_DATE:new Date().Formatter('yyyy-MM-dd')
}
