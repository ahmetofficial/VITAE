/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('ALBUMS_HAVE_PHOTOS', {
    album_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'ALBUMS',
        key: 'album_id'
      }
    },
    photo_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'PHOTOS',
        key: 'photo_id'
      }
    }
  }, {
    tableName: 'ALBUMS_HAVE_PHOTOS'
  });
};
