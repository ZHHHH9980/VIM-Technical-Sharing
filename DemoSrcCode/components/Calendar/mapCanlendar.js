// 目标：生成某年某月的日历表格

// @deprecated - 旧版实现
function mapCalanderV1(month, year) {
  const daysInMonth = (m, y) => new Date(y, m + 1, 0).getDate();
  const days = daysInMonth(month, year);

  return Array(days)
    .fill(0)
    .map((_, i) => <div key={i}>{i + 1}</div>);
}

// line:10 拼写错误 Calander → Calendar
function mapCalendarV2(month, year) {
  const days = new Date(year, month + 1, 0).getDate();

  const weeks = [];
  let week = [];

  for (let day = 1; day <= days; day++) {
    week.push(day);
    if (week.length === 7 || day === days) {
      weeks.push(week); // VIM 操作：ci) 修改数组
      week = [];
    }
  }

  return (
    <div className="my-canlendar">
      {weeks.map((week, i) => (
        <div key={i} className="week">
          {week.map((day) => (
            <div key={day} className="day">
              {day}
            </div>
          ))}
        </div>
      ))}
    </div>
  );
}

export { mapCalanderV1, mapCalendarV2 };
