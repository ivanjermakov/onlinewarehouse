import { OnlineWarehousePage } from './app.po';

describe('online-warehouse App', function() {
  let page: OnlineWarehousePage;

  beforeEach(() => {
    page = new OnlineWarehousePage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
