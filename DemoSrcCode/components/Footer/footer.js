// --------------------- footer.js ---------------------
// 页面页脚模块 (依赖common.js)

// 构建动态页脚内容
const footerBuilder = () => {
  // TODO: add generateVersion from '???' (search)
  // const version = _("build");

  // TODO: add getCurrentYear() from '???'
  return `
        <footer class="site-footer">
            <div class="copyright">
                ©CopyRight ${_()} 示例公司 版本号：${version}
            </div>
            <div class="legal-links">
                <a href="/privacy">隐私政策</a>
                <a href="/terms">服务条款</a>
            </div>
        </footer>
    `;
};

// 初始化页脚
document.addEventListener("DOMContentLoaded", () => {
  document.body.insertAdjacentHTML("beforeend", footerBuilder());
});
